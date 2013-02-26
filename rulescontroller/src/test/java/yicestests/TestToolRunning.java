package yicestests;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mbeddr.ext.utils.tools.ToolRunnerBase;
import com.mbeddr.ext.utils.tools.ToolRunnerBase.ToolRunResult;

public class TestToolRunning {

	private static String testDir = System.getProperty("java.io.tmpdir");
	private static boolean FilePrepared = false;
	private static String YsFileName = testDir + File.separator + "sample.test.ys";

	
	
	@BeforeClass
	public static void createFile()
	{
		FilePrepared = prepare();
	}
	
	@AfterClass
	public static void cleanUp()
	{
		File tmp = new File(YsFileName);
		tmp.delete();
	}
	
	private static boolean prepare() {
		boolean result = false;

		final URL resourceUrl = TestToolRunning.class.getClassLoader().getResource("sample.test.ys");

		byte[] buffer = new byte[1024];
		int byteCount = 0;

		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		
		

		try {
			inputStream = resourceUrl.openStream();
			outputStream = new FileOutputStream(YsFileName);

			while ((byteCount = inputStream.read(buffer)) >= 0) {
				outputStream.write(buffer, 0, byteCount);
			}

			// report success
			result = true;
		} catch (final IOException e) {
			result = false;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (final IOException e) {
					result = false;
				}
			}
			if (outputStream != null) {
				try {
					outputStream.flush();
					outputStream.close();
				} catch (final IOException e) {
					result = false;
				}
			}
		}

		return result;

	}

	@Test
	public void test() {
		if(!FilePrepared)
		{
			fail("Failed to prepare the test file!");
			return;
		}

		ToolRunnerBase yicesRunner = new ToolRunnerBase();
		
		ToolRunResult res;
		
		try {
			res = yicesRunner.runTool(YsFileName, "yices");
		} catch (Exception e) {
			fail("Tool running failed with exception " + e.getStackTrace());
			return;
		}
		
		String s = res.getOutputString();
		
		if(!s.equals("unsat\n"))
		{
			fail("Yices does not seem to work!");
		}
				
	}

}
