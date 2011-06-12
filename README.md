# Groovy JChemPaint

Disclaimer: the code examples in this repository require the CDK-JChemPaint patch, and not the JChemPaint 3.0 applet code. The required jars are provided in this repository.

This is the repository matching the scripts listed in the following blog posts:

* [CDK-JChemPaint #1: rendering molecules](http://chem-bla-ics.blogspot.com/2010/04/cdk-jchempaint-1-rendering-molecules.html)
* [CDK-JChemPaint #2: rendering reactions](http://chem-bla-ics.blogspot.com/2010/04/cdk-jchempaint-2-rendering-reactions.html)
* [CDK-JChemPaint #3: rendering parameters](http://chem-bla-ics.blogspot.com/2010/04/cdk-jchempaint-3-rendering-parameters.html)
* [CDK-JChemPaint #4: embedding the renderer into a Swing panel](http://chem-bla-ics.blogspot.com/2010/04/cdk-jchempaint-4-embedding-renderer.html)
* [CDK-JChemPaint #6: rendering atom numbers](http://chem-bla-ics.blogspot.com/2010/06/cdk-jchempaint-6-rendering-atom-numbers.html)
* [CDK-JChemPaint #7: rendering molecules as SVG](http://chem-bla-ics.blogspot.com/2011/06/cdk-jchempaint-7-rendering-molecules-as.html)

## Running Groovy from the command line

I am not sure how this works on OS/X or Windows (please let me know!), but you can run the scripts provided here on GNU/Linux systems with:

    $ export CLASSPATH=cdk-1.3.11.1.jar:cdk-jchempaint-20.jar
    $ groovy script.groovy

where script.groovy is one of the scripts provided in this repository.
