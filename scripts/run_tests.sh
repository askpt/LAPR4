#!/bin/sh
java -cp ../tmp-build-tests:../dist/csheets.jar:../lib/antlr.jar:../lib/junit-4.10.jar org.junit.runner.JUnitCore csheets.core.WorkbookTest csheets.core.SpreadsheetTest csheets.ext.comments.CommentableCellTest csheets.core.ext.share.ValidateTest csheets.core.ext.share.SendReceiveTest

