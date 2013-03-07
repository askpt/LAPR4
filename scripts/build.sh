#!/bin/sh
javac -cp ../src:../lib/antlr.jar:../lib/junit-4.10.jar -d ../tmp-build ../src/csheets/*.java ../src/csheets/core/*.java ../src/csheets/ext/*.java ../src/csheets/io/*.java ../src/csheets/ui/*.java ../src/csheets/core/formula/*.java ../src/csheets/core/formula/compiler/*.java ../src/csheets/core/formula/lang/*.java ../src/csheets/core/formula/util/*.java ../src/csheets/ext/assertion/*.java ../src/csheets/ext/deptree/*.java ../src/csheets/ext/test/*.java ../src/csheets/ext/assertion/ui/*.java ../src/csheets/ext/test/ui/*.java ../src/csheets/ext/style/*.java ../src/csheets/ext/style/ui/*.java ../src/csheets/ui/ctrl/*.java ../src/csheets/ui/ext/*.java ../src/csheets/ui/sheet/*.java ../src/csheets/ext/simple/*.java ../src-tests/csheets/core/*.java 
