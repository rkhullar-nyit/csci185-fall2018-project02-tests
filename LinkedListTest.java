import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;

import static org.junit.Assert.*;

public class LinkedListTest
{

    private static <T> LinkedListNode[] build_node_array(T...data)
    {
        LinkedListNode nodes[] = new LinkedListNode[data.length];
        for (int i=0; i<data.length; i++)
            nodes[i] = new LinkedListNode<>(data[i]);
        return nodes;
    }

    private static void link_node_array(LinkedListNode nodes[])
    {
        int n = nodes.length;
        for(int i=0; i<n-1; i++)
        {
            nodes[i].next = nodes[i+1];
            nodes[n-i-1].prev = nodes[n-i-2];
        }
    }

    private static LinkedList build_list(LinkedListNode head, LinkedListNode tail, int size) throws NoSuchFieldException, IllegalAccessException
    {
        final String[] field_names = {"head", "tail", "size"};
        HashMap<String, Field> fields = new HashMap<>();

        for(String name: field_names)
        {
            Field field = LinkedList.class.getDeclaredField(name);
            field.setAccessible(true);
            fields.put(name, field);
        }

        LinkedList list = new LinkedList();
        fields.get("head").set(list, head);
        fields.get("tail").set(list, tail);
        fields.get("size").set(list, size);

        return list;
    }

    private static LinkedList build_list(LinkedListNode nodes[]) throws NoSuchFieldException, IllegalAccessException
    {
        return build_list(nodes[0], nodes[nodes.length-1], nodes.length);
    }

    @Test
    public void test_node_data()
    {
        char data = 'x';
        LinkedListNode<Character> node = new LinkedListNode<>(data);
        assertEquals(data, (char) node.getData());
    }

    @Test
    public void test_node_next()
    {
        LinkedListNode nodes[] = build_node_array('a', 'b', 'c');
        nodes[0].next = nodes[1]; nodes[1].next = nodes[2];
        assertTrue(nodes[0].hasNext() && nodes[1].hasNext());
        assertFalse(nodes[2].hasNext());
    }

    @Test
    public void test_node_prev()
    {
        LinkedListNode nodes[] = build_node_array('a', 'b', 'c');
        nodes[1].prev = nodes[0]; nodes[2].prev = nodes[1];
        assertTrue(nodes[1].hasPrev() && nodes[2].hasPrev());
        assertFalse(nodes[0].hasPrev());
    }

    @Test
    public void test_node_to_string()
    {
        LinkedListNode<Character> node = new LinkedListNode<>('x');
        assertEquals("<x>", node.toString());
    }

    @Test
    public void test_list_to_string() throws NoSuchFieldException, IllegalAccessException
    {
        LinkedListNode nodes[] = build_node_array('a', 'b', 'c');
        link_node_array(nodes);
        LinkedList list = build_list(nodes);
        assertEquals("[a, b, c]", list.toString());
    }

}