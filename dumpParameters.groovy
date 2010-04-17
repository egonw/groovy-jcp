import java.util.List;

import org.openscience.cdk.renderer.*;
import org.openscience.cdk.renderer.font.*;
import org.openscience.cdk.renderer.generators.*;
import org.openscience.cdk.renderer.visitor.*;

// generators make the image elements
List<IGenerator> generators = new ArrayList<IGenerator>();
generators.add(new BasicSceneGenerator());
generators.add(new BasicBondGenerator());
generators.add(new BasicAtomGenerator());

// the renderer needs to have a toolkit-specific font manager
IRenderer renderer = new AtomContainerRenderer(
  generators, new AWTFontManager()
);

// dump all parameters
for (IGenerator generator : renderer.getGenerators()) {
  for (IGeneratorParameter parameter : generator.getParameters()) {
    println "parameter: " +
      parameter.getClass().getName().substring(40) +
      " -> " +
      parameter.getValue();
  }
}

