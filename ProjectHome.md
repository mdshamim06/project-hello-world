# Project Hello World  #

## Introduction ##
Project Hello World is a collection of tiny sample codes for various things you can do in java with external libraries, which includes (but not limited to):

  * Parsing xml with **DOM** and **SAX**
  * Reading a resource from classpath
  * Compressing in / decompressing **Zip** files
  * Creating an Excel spreadsheet with **jexcelapi**
  * Sending email from **javax.mail** using the Gmail SMTP
  * Creating a **Sesame** (Open RDF) repository
  * Invoking a **UIMA** analysis engine from a Javacode
  * Providing a command line interface using **commons-cli**
  * Executing an external process using **commons-exec**
  * Generating a plot graph using **jfreechart**
  * Reading, writing and manipulating **json**
  * Taking a **diff** of two sequences
  * Encrypting a string in **MD5**

## Policy ##
  * **Minimum viable code** - No fancy best practices used ... to keep focused on "hello-worlding". Example: I use System.out.println() instead of log4j logging.
  * **Lightweight** - Dependent libraries (jar files) are not included, but they are automatically downloaded by maven once you try compiling the codebase.

## Code ##
  * Go to [Downloads page](http://code.google.com/p/project-hello-world/adminDownloads) to download the codebase (latest code may not be available for this version).
  * Or, browse the [trunk](http://code.google.com/p/project-hello-world/source/browse/#svn%2Ftrunk%2Fsrc%2Fmain%2Fjava%2Fcom%2Fgmail%2Fhidekishima%2Fhelloworld)
  * Or, go to the [Source](http://code.google.com/p/project-hello-world/source/checkout) page to check out the project via Subversion.

In order to actually build and run some codes, you need maven (in eclipse, use [m2eclipse](http://eclipse.org/m2e/) plugin) to fetch depending jar files.