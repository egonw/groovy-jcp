import java.util.List;

import java.awt.*;
import java.awt.image.*;

import org.openscience.cdk.*;
import org.openscience.cdk.interfaces.*;
import org.openscience.cdk.layout.*;
import org.openscience.cdk.renderer.*;
import org.openscience.cdk.renderer.font.*;
import org.openscience.cdk.renderer.generators.*;
import org.openscience.cdk.renderer.visitor.*;
import org.openscience.cdk.templates.*;

import javax.swing.*;

int WIDTH = 600;
int HEIGHT = 600;

class JCPPanel extends JPanel {

  IAtomContainer mol;
  AtomContainerRenderer renderer;
  int width;
  int height;

  public JCPPanel(IAtomContainer mol, int width, int height) {
    super();
    this.setSize(width, height);
    this.mol = mol;
    this.width = width;
    this.height = height;

    // generators make the image elements
    List<IGenerator> generators = new ArrayList<IGenerator>();
    generators.add(new BasicSceneGenerator());
    generators.add(new BasicBondGenerator());
    generators.add(new BasicAtomGenerator());

    // the renderer needs to have a toolkit-specific font manager
    renderer =
      new AtomContainerRenderer(generators, new AWTFontManager());
  }

  public Dimension getPreferredSize() {
    return new Dimension(width, height);
  }

  public void paint(Graphics graphics) {
    // the call to 'setup' only needs to be done on the first paint
    renderer.setup(mol, new Rectangle(getWidth(), getHeight()));

    // paint the background
    graphics.setColor(Color.WHITE);
    graphics.fillRect(0, 0, getWidth(), getHeight());

    // the paint method also needs a toolkit-specific renderer
    renderer.paint(mol, new AWTDrawVisitor(graphics));
  }

}

// create molecule
IAtomContainer triazole = MoleculeFactory.make123Triazole();
StructureDiagramGenerator sdg = new StructureDiagramGenerator();
sdg.setMolecule(triazole);
sdg.generateCoordinates();
triazole = sdg.getMolecule();

JFrame frame = new JFrame("Swinging CDK-JChemPaint");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

JCPPanel panel = new JCPPanel(triazole, WIDTH, HEIGHT);
frame.getContentPane().add(panel);

frame.pack();
frame.setVisible(true);

