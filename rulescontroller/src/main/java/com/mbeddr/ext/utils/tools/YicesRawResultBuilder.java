package com.mbeddr.ext.utils.tools;

/*Generated by MPS */
//AND THEN CORRECTED!

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class YicesRawResultBuilder {
	public YicesRawResultBuilder() {
	}

	public List<YicesRawResult> buildModel(List<String> outputLines)
			throws IOException {
		List<YicesRawResult> rawResults = new ArrayList<YicesRawResult>();
		YicesRawResult currentYAR = new YicesRawResult();
		boolean flag = false;

		for (String line : outputLines) {
			if (line == null) {
				continue;
			}

			line = line.trim();

			if (line.isEmpty()) {
				continue;
			}

			if ("sat".equals(line) || "unsat".equals(line)) {
				currentYAR = new YicesRawResult();
				rawResults.add(currentYAR);
				flag = false;

				if (line.equals("unsat")) {
					currentYAR.setSatisfiable(false);
				} else {
					currentYAR.setSatisfiable(true);
					flag = true;
				}

			}

			if (flag && line.startsWith("(")) {
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
					currentYAR.addVarValMapping(varName, val);
				}
			}

			if (line.startsWith("unsat core ids:")) {
				String idsAsString = line.substring(line.indexOf(":") + 1);
				String[] idsAsStringArray = idsAsString.split(" ");
				for (String it : idsAsStringArray) {
					it = it.trim();
					if (!(it.equals(""))) {
						try {
							currentYAR.addUnsatCoreID(Integer.parseInt(it));
						} catch (NumberFormatException nfe) {
							nfe.printStackTrace();
						}
					}
				}
			}

		}

		return rawResults;
	}
}
