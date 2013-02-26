package com.mbeddr.ext.utils.tools;

/*Generated by MPS */

import java.util.concurrent.TimeoutException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import jetbrains.mps.internal.collections.runtime.ListSequence;
import jetbrains.mps.internal.collections.runtime.backports.LinkedList;
import java.util.List;
import java.io.File;
import jetbrains.mps.internal.collections.runtime.ILeftCombinator;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import jetbrains.mps.internal.collections.runtime.IVisitor;

public abstract class ToolRunnerBase {
  private String toolBinaryName;
  public boolean analysisCanceled = false;
  private Process myToolProcess;
  private String myUserFriendlyAnalysisName;

  public ToolRunnerBase() {
  }

  public ToolRunnerBase.ToolRunResult runTool(String pathToGeneratedFile, String toolBinaryName) throws TimeoutException, IOException, ExecutionException, InterruptedException {
    return runTool(pathToGeneratedFile, toolBinaryName, ListSequence.fromList(new LinkedList<String>()));
  }

  public ToolRunnerBase.ToolRunResult runTool(String pathToGeneratedFile, String toolBinaryName, List<String> argsList) throws IOException, TimeoutException, ExecutionException, InterruptedException {
    return runTool(pathToGeneratedFile, toolBinaryName, argsList, null);
  }

  public ToolRunnerBase.ToolRunResult runTool(String pathToGeneratedFile, String toolBinaryName, List<String> argsList, File dir) throws IOException, TimeoutException, ExecutionException, InterruptedException {
    this.toolBinaryName = toolBinaryName;
    String argsString = ListSequence.fromList(argsList).foldLeft("", new ILeftCombinator<String, String>() {
      public String combine(String s, String it) {
        return s + it + " ";
      }
    });

    long startingTimeInMillis = System.currentTimeMillis();
    ToolRunnerBase.this.setToolProcess(doRunToolWithArgs(pathToGeneratedFile, toolBinaryName, argsString, dir));

    ToolRunnerBase.StreamReaderThread outputReaderThread = new ToolRunnerBase.StreamReaderThread(ToolRunnerBase.this.getToolProcess().getInputStream());
    ToolRunnerBase.StreamReaderThread errorReaderThread = new ToolRunnerBase.StreamReaderThread(ToolRunnerBase.this.getToolProcess().getErrorStream());


    outputReaderThread.start();
    errorReaderThread.start();

    ToolRunnerBase.this.getToolProcess().waitFor();

    outputReaderThread.join();
    errorReaderThread.join();

    List<String> output = outputReaderThread.getReadLines();
    List<String> error = errorReaderThread.getReadLines();

    long delay = System.currentTimeMillis() - startingTimeInMillis;
    System.err.println("Delay: " + delay + "ms");

    return new ToolRunnerBase.ToolRunResult(output, error, delay);
  }

  private Process doRunToolWithArgs(String pathToGeneratedFile, String toolBinaryName, String args, File dir) throws TimeoutException, IOException, ExecutionException, InterruptedException {
    String suffix = ((System.getProperty("path.separator") + "").equals("\\") ?
      ".exe" :
      ""
    );
    String command = toolBinaryName + suffix;
    String[] commands = new String[]{command, args, pathToGeneratedFile};
    return doRunProgram(commands, dir);
  }

  private Process doRunProgram(String[] commands, File dir) throws IOException, TimeoutException, ExecutionException, InterruptedException {
    String cmdString = "";
    for (String c : commands) {
      if ((c != null && c.length() > 0)) {
        cmdString += c + " ";
      }
    }
    if (dir != null) {
      System.err.println("Dir: " + dir.getAbsolutePath());
    }
    System.err.println("Running: " + cmdString);
    final Process ls_proc = Runtime.getRuntime().exec(cmdString, null, dir);
    return ls_proc;
  }

  /*package*/ void doKillExternalProcesses() throws IOException {
    String killCommand = "";
    if (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1) {
      killCommand = "taskkill /F /IM " + toolBinaryName + ".exe";
    } else {
      killCommand = "killall -9 " + toolBinaryName;
    }
    System.out.println("Kill command: " + killCommand);
    Runtime.getRuntime().exec(killCommand);
  }

  public int getTimeoutInMilliseconds() {
    return 2000;
  }

  public Process getToolProcess() {
    return this.myToolProcess;
  }

  private void setToolProcess(Process value) {
    this.myToolProcess = value;
  }

  public String getUserFriendlyAnalysisName() {
    return this.myUserFriendlyAnalysisName;
  }

  protected void setUserFriendlyAnalysisName(String value) {
    this.myUserFriendlyAnalysisName = value;
  }

  private class StreamReaderThread extends Thread {
    private InputStream inputStream;
    private List<String> result = ListSequence.fromList(new LinkedList<String>());
    private IOException thrownException;

    public StreamReaderThread(InputStream is) {
      inputStream = is;
    }

    public void run() {
      try {
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
          ListSequence.fromList(result).addElement(line);
        }
        isr.close();
      } catch (IOException ioe) {
        ioe.printStackTrace();
        thrownException = ioe;
      }
    }

    private List<String> getReadLines() throws IOException {
      if (thrownException != null) {
        throw thrownException;
      }
      return result;
    }
  }

  protected class ToolRunResult {
    public List<String> outputResult;
    public List<String> errorResult;
    private long delay;

    public ToolRunResult(List<String> output, List<String> error, long delay) {
      outputResult = output;
      errorResult = error;
      this.delay = delay;
    }

    public String getOutputString() {
      final StringBuffer sb = new StringBuffer();
      ListSequence.fromList(outputResult).visitAll(new IVisitor<String>() {
        public void visit(String it) {
          sb.append(it).append("\n");
        }
      });
      return sb.toString();
    }

    public String getErrorString() {
      final StringBuffer sb = new StringBuffer();
      ListSequence.fromList(errorResult).visitAll(new IVisitor<String>() {
        public void visit(String it) {
          sb.append(it).append("\n");
        }
      });
      return sb.toString();
    }

    public long getDelay() {
      return delay;
    }
  }
}