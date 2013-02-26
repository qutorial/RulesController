package com.mbeddr.ext.utils.tools;

/*Generated by MPS */
//AND THEN CORRECTED!

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.sound.midi.Sequence;

public class YicesRawResultBuilder {
  public YicesRawResultBuilder() {
  }

  public List<YicesRawResult> buildModel(List<String> outputLines) throws IOException {
    final List<YicesRawResult> rawResults = new ArrayList<YicesRawResult>();
    final Wrappers._T<YicesRawResult> currentYAR = new Wrappers._T<YicesRawResult>(new YicesRawResult());
    final Wrappers._boolean flag = new Wrappers._boolean(false);
    ListSequence.fromList(outputLines).visitAll(new IVisitor<String>() {
      public void visit(String line) {
        if (((line == null ?
          null :
          line.trim()
        )).equals("unsat") || ((line == null ?
          null :
          line.trim()
        )).equals("sat")) {
          currentYAR.value = new YicesRawResult();
          ListSequence.fromList(rawResults).addElement(currentYAR.value);
          flag.value = false;
        }
        if (line.trim().equals("unsat")) {
          currentYAR.value.setSatisfiable(false);
        }
        if (((line == null ?
          null :
          line.trim()
        )).equals("sat")) {
          currentYAR.value.setSatisfiable(true);
          flag.value = true;
        }
        if (flag.value && line.startsWith("(")) {
          StringTokenizer st = new StringTokenizer(line, "()= ");
          if (!(line.contains("mk-record"))) {
            String varName = st.nextToken();
            String val;
            if (!(varName.equals("select"))) {
              val = st.nextToken();
            } else {
              String structName = st.nextToken();
              String memName = st.nextToken();
              varName = structName + "." + memName;
              val = st.nextToken();
            }
            currentYAR.value.addVarValMapping(varName, val);

          }
        }
        if (line.startsWith("unsat core ids:")) {
          String idsAsString = line.substring(line.indexOf(":") + 1);
          Iterable<String> idsAsStringArray = Sequence.fromArray(idsAsString.split(" "));
          Sequence.fromIterable(idsAsStringArray).visitAll(new IVisitor<String>() {
            public void visit(String it) {
              it = it.trim();
              if (!(it.equals(""))) {
                try {
                  currentYAR.value.addUnsatCoreID(Integer.parseInt(it));
                } catch (NumberFormatException nfe) {
                  nfe.printStackTrace();
                }
              }
            }
          });
        }
      }
    });

    return rawResults;
  }
}
