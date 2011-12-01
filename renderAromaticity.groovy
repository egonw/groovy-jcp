import java.util.List;

import java.awt.*;
import java.awt.image.*;

import javax.imageio.*;

import org.openscience.cdk.*;
import org.openscience.cdk.interfaces.*;
import org.openscience.cdk.layout.*;
import org.openscience.cdk.renderer.*;
import org.openscience.cdk.renderer.font.*;
import org.openscience.cdk.renderer.generators.*;
import org.openscience.cdk.renderer.visitor.*;
import org.openscience.cdk.templates.*;

int WIDTH = 600;
int HEIGHT = 600;

// the draw area and the image should be the same size
Rectangle drawArea = new Rectangle(WIDTH, HEIGHT);
Image image = new BufferedImage(
  WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB
);

IMolecule benzene = MoleculeFactory.makeBenzene();
benzene.atoms().each { atom ->
  atom.setFlag(CDKConstants.ISAROMATIC, true)
}
benzene.bonds().each { bond ->
  bond.setFlag(CDKConstants.ISAROMATIC, true)
}

StructureDiagramGenerator sdg = new StructureDiagramGenerator();
sdg.setMolecule(benzene);
sdg.generateCoordinates();
benzene = sdg.getMolecule();

// generators make the image elements
List<IGenerator> generators = new ArrayList<IGenerator>();
generators.add(new BasicSceneGenerator());
generators.add(new BasicBondGenerator());
generators.add(new BasicAtomGenerator());

// the renderer needs to have a toolkit-specific font manager
AtomContainerRenderer renderer =
  new AtomContainerRenderer(generators, new AWTFontManager());

// the call to 'setup' only needs to be done on the first paint
renderer.setup(benzene, drawArea);

// paint the background
Graphics2D g2 = (Graphics2D)image.getGraphics();
g2.setColor(Color.WHITE);
g2.fillRect(0, 0, WIDTH, HEIGHT);

// the paint method also needs a toolkit-specific renderer
renderer.paint(benzene, new AWTDrawVisitor(g2));

ImageIO.write((RenderedImage)image, "PNG", new File("benzene.png"));



// and now with an aromatic ring
generators.clear();
generators.add(new BasicSceneGenerator());
generators.add(new RingGenerator());
generators.add(new BasicAtomGenerator());

// paint the background
image = new BufferedImage(
  WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB
);

g2 = (Graphics2D)image.getGraphics();
g2.setColor(Color.WHITE);
g2.fillRect(0, 0, WIDTH, HEIGHT);

// the paint method also needs a toolkit-specific renderer
renderer.paint(benzene, new AWTDrawVisitor(g2));

ImageIO.write((RenderedImage)image, "PNG", new File("benzene2.png"));