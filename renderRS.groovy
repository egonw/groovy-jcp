import java.util.List;

import java.awt.*;
import java.awt.image.*;

import javax.imageio.*;
import javax.vecmath.*;

import org.openscience.cdk.*;
import org.openscience.cdk.geometry.cip.*;
import org.openscience.cdk.geometry.cip.CIPTool.CIP_CHIRALITY;
import org.openscience.cdk.interfaces.*;
import org.openscience.cdk.layout.*;
import org.openscience.cdk.renderer.*;
import org.openscience.cdk.renderer.font.*;
import org.openscience.cdk.renderer.generators.*;
import org.openscience.cdk.renderer.visitor.*;
import org.openscience.cdk.renderer.elements.*;
import org.openscience.cdk.renderer.generators.parameter.*;
import org.openscience.cdk.renderer.generators.BasicSceneGenerator.Scale;
import org.openscience.cdk.smiles.*;

int WIDTH = 200;
int HEIGHT = 200;

smiles = "Cl[C@@]([H])(Br)C[C@@H](F)O";

class RSGenerator implements IGenerator<IAtomContainer> {

  public static class TextColor
  extends AbstractGeneratorParameter<Color> {
    public Color getDefault() {
      return Color.BLACK;
    }
  }

  private IGeneratorParameter<Color> textColor = new TextColor();
    
  public static class Offset
  extends AbstractGeneratorParameter<Vector2d> {
    public Vector2d getDefault() {
      return new Vector2d();
    }
  }
  private Offset offset = new Offset();

  public List<IGeneratorParameter<?>> getParameters() {
    List list = new ArrayList();
    list.add(textColor);
    list.add(offset);
    return list;
  };

  public IRenderingElement generate(IAtomContainer container, RendererModel model) {
    ElementGroup rsIndicators = new ElementGroup();
    Vector2d offset = new Vector2d(
      this.offset.getValue().x, -this.offset.getValue().y
    );
    offset.scale( 1/model.getParameter(Scale.class).getValue() );

    Iterator<IStereoElement> stereoElements = container.stereoElements().iterator();
    for (IStereoElement stereo : stereoElements) {
      if (stereo instanceof ITetrahedralChirality) {
        ITetrahedralChirality l4Chiral = (ITetrahedralChirality)stereo;
        CIP_CHIRALITY chirality = CIPTool.getCIPChirality(container, stereo);
        String stereoText = (chirality == CIP_CHIRALITY.R ? "R" : "S");
        if (chirality == CIP_CHIRALITY.NONE) stereoText = "?";
        IAtom atom = l4Chiral.getChiralAtom();
        Point2d point = new Point2d(atom.getPoint2d());
        point.add( offset );
        rsIndicators.add(
          new TextElement(
            point.x, point.y,
            stereoText,
            textColor.getValue()
          )
        );
      }
    }
    return rsIndicators;
  };
}

// the draw area and the image should be the same size
Rectangle drawArea = new Rectangle(WIDTH, HEIGHT);
Image image = new BufferedImage(
  WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB
);

IAtomContainer mol = new SmilesParser(
  DefaultChemObjectBuilder.getInstance()
).parseSmiles(smiles);
StructureDiagramGenerator sdg = new StructureDiagramGenerator();
sdg.setMolecule(mol);
sdg.generateCoordinates();
molWithCoords = sdg.getMolecule();
// copy coordinates
for (int i=0; i<molWithCoords.getAtomCount(); i++) {
  mol.getAtom(i).setPoint2d(molWithCoords.getAtom(i).getPoint2d());
}

// generators make the image elements
List<IGenerator> generators = new ArrayList<IGenerator>();
generators.add(new BasicSceneGenerator());
generators.add(new BasicAtomGenerator());
generators.add(new BasicBondGenerator());
generators.add(new RSGenerator());

// the renderer needs to have a toolkit-specific font manager
AtomContainerRenderer renderer =
  new AtomContainerRenderer(generators, new AWTFontManager());

// the call to 'setup' only needs to be done on the first paint
renderer.setup(mol, drawArea);

// tune parameters
model = renderer.getRenderer2DModel();
model.set(
  RSGenerator.TextColor.class,
  Color.orange
);
model.set(
  RSGenerator.Offset.class,
  new Vector2d(10,10)
);

// paint the background
Graphics2D g2 = (Graphics2D)image.getGraphics();
g2.setColor(Color.WHITE);
g2.fillRect(0, 0, WIDTH, HEIGHT);

// the paint method also needs a toolkit-specific renderer
renderer.paint(mol, new AWTDrawVisitor(g2));

ImageIO.write((RenderedImage)image, "PNG", new File("smiles.rs.png"));

