package java2.application_target_list.core.junittests.database;

import java2.application_target_list.core.database.target.TargetRepository;
import java2.application_target_list.core.database.target.InMemoryTargetRepositoryImpl;
import java2.application_target_list.core.domain.Target;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InMemoryTargetRepositoryImplTest {

    private TargetRepository targetsDatabase;

    @Before
    public void setup() {
        targetsDatabase = new InMemoryTargetRepositoryImpl();
        Target firstTarget = new Target("Eat", "need to eat", 1L);
        Target secondTarget = new Target("Eat more", "need to eat more", 5L);
        Target thirdTarget = new Target("name", "description", 5L);
        targetsDatabase.addTarget(firstTarget);
        targetsDatabase.addTarget(secondTarget);
        targetsDatabase.addTarget(thirdTarget);
    }

    @Test
    public void testSearchTargetByName_v3(){
        List<Target> targetList = targetsDatabase.findByTargetName("name");
        Assert.assertEquals(targetList.size(), 1);
    }

    @Test
    public void testSearchTargetByName_v1(){
        List<Target> targetList = targetsDatabase.findByTargetName("Eat");
        Assert.assertEquals(targetList.size(), 2);
    }

    @Test
    public void testSearchTargetByName_v2(){
        List<Target> targetList = targetsDatabase.findByTargetName("a");
        Assert.assertEquals(targetList.size(), 3);
    }

    @Test
    public void testSearchTargetByDescription_v1(){
        List<Target> targetList = targetsDatabase.findByTargetDescription("description");
        Assert.assertEquals(targetList.size(), 1);
    }

    @Test
    public void testSearchTargetByDescription_v2(){
        List<Target> targetList = targetsDatabase.findByTargetDescription("need");
        Assert.assertEquals(targetList.size(), 2);
    }


    @Test
    public void testAddTarget_list_size() {
        Assert.assertEquals(targetsDatabase.getTargetsList().size(), 3);
    }


    @Test
    public void testAddTarget_targetId_v1() {
        Long actual = targetsDatabase.getTargetsList().get(0).getId();
        Long expected = 1L;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testAddTarget_targetId_v2() {
        Long actual = targetsDatabase.getTargetsList().get(1).getId();
        Long expected = 2L;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testAddTarget_targetDeleteById_v1() {
        targetsDatabase.deleteTarget(2L);
        Assert.assertEquals(targetsDatabase.getTargetsList().size(), 2);
        Assert.assertEquals(targetsDatabase.getTargetsList().get(0).getName(), "Eat");
        Assert.assertEquals(targetsDatabase.getTargetsList().get(1).getName(), "name");
    }

    @Test
    public void testAddTarget_targetDeleteById_v2() {
        targetsDatabase.deleteTarget(1L);
        Assert.assertEquals(targetsDatabase.getTargetsList().size(), 2);
        Assert.assertEquals(targetsDatabase.getTargetsList().get(0).getName(), "Eat more");
        Assert.assertEquals(targetsDatabase.getTargetsList().get(1).getName(), "name");
    }


    @Test
    public void testAddTarget_changeTargetName_v1() {
        String newTargetName = "new name";
        targetsDatabase.changeTargetName(1L, newTargetName);
        Assert.assertEquals(targetsDatabase.getTargetsList().get(0).getName(), newTargetName);
    }

    @Test
    public void testAddTarget_changeTargetName_v2() {
        String newTargetName = "new name";
        targetsDatabase.changeTargetName(2L, newTargetName);
        Assert.assertEquals(targetsDatabase.getTargetsList().get(1).getName(), newTargetName);
    }

    @Test
    public void testAddTarget_changeTargetDescription_v1() {
        String newTargetDescription = "new description";
        targetsDatabase.changeTargetDescription(1L, newTargetDescription);
        Assert.assertEquals(targetsDatabase.getTargetsList().get(0).getDescription(), newTargetDescription);
    }

    @Test
    public void testAddTarget_changeTargetDescription_v2() {
        String newTargetDescription = "new description";
        targetsDatabase.changeTargetDescription(2L, newTargetDescription);
        Assert.assertEquals(targetsDatabase.getTargetsList().get(1).getDescription(), newTargetDescription);
    }

    @Test
    public void testAddTarget_changeTargetDeadline_v1() {
        targetsDatabase.changeTargetDeadline(1L, 2L);
        Assert.assertEquals(Optional.ofNullable(targetsDatabase.getTargetsList().get(0).getDeadline()), Optional.of(2L));
    }

    @Test
    public void testAddTarget_changeTargetDeadline_v2() {
        targetsDatabase.changeTargetDeadline(2L, 2L);
        Assert.assertEquals(Optional.ofNullable(targetsDatabase.getTargetsList().get(1).getDeadline()), Optional.of(2L));
    }
}