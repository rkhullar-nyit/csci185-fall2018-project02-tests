import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.HashMap;

class TestRunner
{

    private static final int max = 10, min = 1;
    private static final HashMap<String, Integer> penalties = new HashMap<>();

    static
    {
        penalties.put("test_node_data", 1);
        penalties.put("test_node_next", 1);
        penalties.put("test_node_prev", 1);
        penalties.put("test_node_to_string", 1);
        penalties.put("test_list_to_string", 2);
        penalties.put("test_list_add_head", 2);
        penalties.put("test_list_add_tail", 2);
        penalties.put("test_list_del_head", 2);
        penalties.put("test_list_del_tail", 2);
    }

    public static void main(String[] args)
    {
        Result linked_list_result = JUnitCore.runClasses(LinkedListTest.class);
        Result[] results = new Result[]{linked_list_result};

        int grade = max;

        for(Result result: results)
        {
            for (Failure failure : result.getFailures())
            {
                Description description = failure.getDescription();
                String class_name = description.getClassName();
                String method_name = description.getMethodName();
                String message = failure.getMessage();

                System.err.printf("failed test %s.%s\n\t%s\n", class_name, method_name, message);

                grade -= penalties.get(method_name.split("[\\[]")[0]);
            }
        }

        if(grade < min)
            grade = min;

        System.out.println(grade);
    }
}
