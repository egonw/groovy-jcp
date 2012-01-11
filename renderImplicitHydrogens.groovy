import java.util.List;

import java.awt.*;
import java.awt.image.*;

import javax.imageio.*;
import javax.vecmath.*;

import org.openscience.cdk.silent.*;
import org.openscience.cdk.interfaces.*;
import org.openscience.cdk.layout.*;
import org.openscience.cdk.renderer.*;
import org.openscience.cdk.renderer.font.*;
import org.openscience.cdk.renderer.generators.*;
import org.openscience.cdk.renderer.visitor.*;
import org.openscience.cdk.templates.*;

int WIDTH = 200;
int HEIGHT = 200;

// the draw area and the image should be the same size
Rectangle drawArea = new Rectangle(WIDTH, HEIGHT);
Image image = new BufferedImage(
  WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB
);

IMolecule methane = new Molecule();
atom = new Atom("C");
methane.addAtom(atom);
atom.setPoint2d(new Point2d(0,0));
atom.setImplicitHydrogenCount(4);

// generators make the image elements
List<IGenerator> generators = new ArrayList<IGenerator>();
generators.add(new BasicSceneGenerator());
generators.add(new BasicBondGenerator());
generators.add(new ExtendedAtomGenerator());

// the renderer needs to have a toolkit-specific font manager
AtomContainerRenderer renderer =
  new AtomContainerRenderer(generators, new AWTFontManager());

// the call to 'setup' only needs to be done on the first paint
renderer.setup(methane, drawArea);

// paint the background
Graphics2D g2 = (Graphics2D)image.getGraphics();
g2.setColor(Color.WHITE);
g2.fillRect(0, 0, WIDTH, HEIGHT);

// the paint method also needs a toolkit-specific renderer
renderer.paint(methane, new AWTDrawVisitor(g2));

ImageIO.write((RenderedImage)image, "PNG", new File("methane.png"));

