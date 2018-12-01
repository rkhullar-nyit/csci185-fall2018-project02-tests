import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class MockIOTest
{

    private static final String line_ending = System.lineSeparator();
    private PrintStream std_out;
    private InputStream std_in;
    private ByteArrayOutputStream mock_out;
    private ByteArrayInputStream mock_in;

    public static String build_output(String... array)
    {
        StringBuilder builder = new StringBuilder();
        for(String line: array)
        {
            builder.append(line);
            builder.append(line_ending);
        }
        return builder.toString().trim();
    }

    public void set_mock_input(String... array)
    {
        byte[] buffer = build_output(array).getBytes();
        mock_in = new ByteArrayInputStream(buffer);
        System.setIn(mock_in);
    }

    public String get_mock_output()
    {
        return mock_out.toString().trim();
    }

    @Before
    public void setUp()
    {
        std_out = System.out;
        std_in = System.in;
        mock_out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(mock_out));
    }

    @After
    public void tearDown()
    {
        System.setOut(std_out);
        System.setIn(std_in);
    }

}
