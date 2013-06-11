#!/bin/sh
echo Compiling...
./build.sh

echo Copying resource files...
cp -R ../src-resources/csheets ../tmp-build 

echo Creating archive...
jar cmf makejar.mf ../dist/csheets.jar -C ../tmp-build csheets

echo Copiar as dependencias
cp ../lib/antlr.jar ../dist/antlr.jar
cp ../lib/hsqldb.jar ../dist/hsqldb.jar
cp ../lib/derby.jar ../dist/derby.jar

cp -R ../src-resources src-resources

# echo Removing temporary files...
# rm -R jar
