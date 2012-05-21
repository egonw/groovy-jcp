# Groovy JChemPaint

Disclaimer: the code examples in this repository require the CDK-JChemPaint patch, and not the JChemPaint 3.0 applet code. The required jars are provided in this repository.

This is the repository matching the scripts listed in the following blog posts:

* [CDK-JChemPaint #1: rendering molecules](http://chem-bla-ics.blogspot.com/2010/04/cdk-jchempaint-1-rendering-molecules.html)
* [CDK-JChemPaint #2: rendering reactions](http://chem-bla-ics.blogspot.com/2010/04/cdk-jchempaint-2-rendering-reactions.html)
* [CDK-JChemPaint #3: rendering parameters](http://chem-bla-ics.blogspot.com/2010/04/cdk-jchempaint-3-rendering-parameters.html)
* [CDK-JChemPaint #4: embedding the renderer into a Swing panel](http://chem-bla-ics.blogspot.com/2010/04/cdk-jchempaint-4-embedding-renderer.html)
* [CDK-JChemPaint #6: rendering atom numbers](http://chem-bla-ics.blogspot.com/2010/06/cdk-jchempaint-6-rendering-atom-numbers.html)
* [CDK-JChemPaint #7: rendering molecules as SVG](http://chem-bla-ics.blogspot.com/2011/06/cdk-jchempaint-7-rendering-molecules-as.html)
* [CDK-JChemPaint #8: rendering of aromatic rings](http://chem-bla-ics.blogspot.com/2011/12/cdk-jchempaint-8-rendering-of-aromatic.html)
* [CDK-JChemPaint #9: implicit hydrogens and isotopes](http://chem-bla-ics.blogspot.com/2011/12/cdk-jchempaint-9-implicit-hydrogens-and.html)
* [CDK-JChemPaint #10: background color](http://chem-bla-ics.blogspot.com/2012/01/cdk-jchempaint-10-background-color.html)

## Running Groovy from the command line

The below commands require you have a checkout of this repository. You can clone it from the command line with something in the line of:

    $ git clone git://github.com/egonw/groovy-jcp.git
    $ cd groovy-jcp

I am not sure how this works on OS/X or Windows (please let me know!), but you can run the scripts provided here on GNU/Linux systems with the following commands.

For the following scripts, you can use a stock CDK release:

    $ export CLASSPATH=cdk-1.4.10.jar
    $ groovy script.groovy

The patch is this one:

https://sourceforge.net/tracker/?func=detail&aid=3369273&group_id=20024&atid=320024

Where script.groovy is one of:

* renderMol.groovy
* dumpParameters.groovy
* renderRS.groovy
* swing.groovy
* greyBG.groovy

For the others you need to rest of the CDK-JChemPaint patch:

    $ export CLASSPATH=cdk-1.4.10.jar:cdk-jchempaint-26.jar
    $ groovy script.groovy

where script.groovy is one of the remaining scripts provided in this repository:

* renderAtomNumbers.groovy
* svgMol.groovy
* renderReaction.groovy
* renderImplicitHydrogens.groovy
* renderIsotopes.groovy
