package com.malfunction.filecmprapp.model;


import com.malfunction.filecmprapp.model.Trigger.EcsTriggers;
import com.malfunction.filecmprapp.model.Trigger.Trigger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EcsTriggerTests {

    @Test
    public void verify_initialize_object(){
        EcsTriggers ecsTriggers = new EcsTriggers();

        assertNotNull(ecsTriggers);

    }
    @Test
    public void verify_add_set_of_triggers(){

        EcsTriggers ecsTriggers = new EcsTriggers();
        ecsTriggers.setSysTriggers(this.getTestTrigger());

        assertEquals(this.getTestTrigger().size(),ecsTriggers.getItems().size());

    }

    @Test
    public void verify_load_items_loads_nonNull_object(){
        EcsTriggers ecsTriggers = new EcsTriggers();
        ecsTriggers.loadItems();
        assertNotNull("List is Null Mal",ecsTriggers.getItems());



    }


    protected List<Trigger> getTestTrigger(){

        List<Trigger> triggers = new ArrayList<>();

        triggers.add(new Trigger("Test1","EVENT"));
        triggers.add(new Trigger("Test2","EVENT"));
        triggers.add(new Trigger("Test3","EVENT"));

        return triggers;
    }
}
