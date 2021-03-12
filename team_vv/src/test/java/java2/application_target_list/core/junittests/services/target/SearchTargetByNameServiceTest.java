package java2.application_target_list.core.junittests.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.requests.Ordering;
import java2.application_target_list.core.requests.Paging;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.requests.target.SearchTargetByNameRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.target.SearchTargetByNameResponse;
import java2.application_target_list.core.services.target.SearchTargetByNameService;
import java2.application_target_list.core.validators.target.SearchTargetByNameValidator;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class SearchTargetByNameServiceTest {

    private List<CoreError> errors;
    private List<Target> targets;
    @Mock
    private SearchTargetByNameValidator validator;
    @Mock
    private JpaTargetRepository jpaTargetRepository;
    @InjectMocks
    SearchTargetByNameService service;

    @Before
    public void setup() {
        ReflectionTestUtils.setField(service, "orderingEnabled", true);
        ReflectionTestUtils.setField(service, "pagingEnabled", true);
        targets = new ArrayList<>();
        errors = new ArrayList<>();
    }

    @Test
    public void shouldSearchTargetsByName() {
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name", "description", 1L));
        Mockito.when(jpaTargetRepository.findByName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertFalse(response.hasErrors());
        Assert.assertEquals(response.getTargetsList().size(), 1);
        Assert.assertEquals(response.getTargetsList().get(0).getName(), "name");
        Assert.assertEquals(response.getTargetsList().get(0).getDescription(), "description");
        Assert.assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(1L));
    }

    @Test
    public void shouldSearchTargetsByNameWithOrderingByNameAscending() {
        Ordering ordering = new Ordering("name", "ASCENDING");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name2", "description2", 1L));
        targets.add(new Target("name1", "description1", 10L));
        Mockito.when(jpaTargetRepository.findByName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertFalse(response.hasErrors());
        Assert.assertEquals(response.getTargetsList().size(), 2);
        Assert.assertEquals(response.getTargetsList().get(0).getName(), "name1");
        Assert.assertEquals(response.getTargetsList().get(0).getDescription(), "description1");
        Assert.assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(10L));
        Assert.assertEquals(response.getTargetsList().get(1).getName(), "name2");
        Assert.assertEquals(response.getTargetsList().get(1).getDescription(), "description2");
        Assert.assertEquals(Optional.ofNullable(response.getTargetsList().get(1).getDeadline()), Optional.of(1L));
    }

    @Test
    public void shouldSearchTargetsByNameWithOrderingByNameDescending() {
        Ordering ordering = new Ordering("name", "DESCENDING");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name1", "description1", 10L));
        targets.add(new Target("name2", "description2", 1L));
        Mockito.when(jpaTargetRepository.findByName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertFalse(response.hasErrors());
        Assert.assertEquals(response.getTargetsList().size(), 2);
        Assert.assertEquals(response.getTargetsList().get(0).getName(), "name2");
        Assert.assertEquals(response.getTargetsList().get(0).getDescription(), "description2");
        Assert.assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(1L));
        Assert.assertEquals(response.getTargetsList().get(1).getName(), "name1");
        Assert.assertEquals(response.getTargetsList().get(1).getDescription(), "description1");
        Assert.assertEquals(Optional.ofNullable(response.getTargetsList().get(1).getDeadline()), Optional.of(10L));
    }

    @Test
    public void shouldSearchTargetsByNameWithOrderingByDescriptionDescending() {
        Ordering ordering = new Ordering("description", "DESCENDING");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name1", "description1", 10L));
        targets.add(new Target("name2", "description2", 1L));
        Mockito.when(jpaTargetRepository.findByName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertFalse(response.hasErrors());
        Assert.assertEquals(response.getTargetsList().size(), 2);
        Assert.assertEquals(response.getTargetsList().get(0).getName(), "name2");
        Assert.assertEquals(response.getTargetsList().get(0).getDescription(), "description2");
        Assert.assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(1L));
        Assert.assertEquals(response.getTargetsList().get(1).getName(), "name1");
        Assert.assertEquals(response.getTargetsList().get(1).getDescription(), "description1");
        Assert.assertEquals(Optional.ofNullable(response.getTargetsList().get(1).getDeadline()), Optional.of(10L));
    }

    @Test
    public void shouldSearchTargetsByNameWithOrderingByDescriptionAscending() {
        Ordering ordering = new Ordering("description", "ASCENDING");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name2", "description2", 1L));
        targets.add(new Target("name1", "description1", 10L));
        Mockito.when(jpaTargetRepository.findByName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertFalse(response.hasErrors());
        Assert.assertEquals(response.getTargetsList().size(), 2);
        Assert.assertEquals(response.getTargetsList().get(0).getName(), "name1");
        Assert.assertEquals(response.getTargetsList().get(0).getDescription(), "description1");
        Assert.assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(10L));
        Assert.assertEquals(response.getTargetsList().get(1).getName(), "name2");
        Assert.assertEquals(response.getTargetsList().get(1).getDescription(), "description2");
        Assert.assertEquals(Optional.ofNullable(response.getTargetsList().get(1).getDeadline()), Optional.of(1L));
    }

    @Test
    public void shouldSearchTargetsByNameWithOrderingByDeadlineAscending() {
        Ordering ordering = new Ordering("deadline", "ASCENDING");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name2", "description2", 10L));
        targets.add(new Target("name1", "description1", 1L));
        Mockito.when(jpaTargetRepository.findByName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertFalse(response.hasErrors());
        Assert.assertEquals(response.getTargetsList().size(), 2);
        Assert.assertEquals(response.getTargetsList().get(0).getName(), "name1");
        Assert.assertEquals(response.getTargetsList().get(0).getDescription(), "description1");
        Assert.assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(1L));
        Assert.assertEquals(response.getTargetsList().get(1).getName(), "name2");
        Assert.assertEquals(response.getTargetsList().get(1).getDescription(), "description2");
        Assert.assertEquals(Optional.ofNullable(response.getTargetsList().get(1).getDeadline()), Optional.of(10L));
    }

    @Test
    public void shouldSearchTargetsByNameWithOrderingByDeadlineDescending() {
        Ordering ordering = new Ordering("deadline", "DESCENDING");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name1", "description1", 1L));
        targets.add(new Target("name2", "description2", 10L));
        Mockito.when(jpaTargetRepository.findByName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertFalse(response.hasErrors());
        Assert.assertEquals(response.getTargetsList().size(), 2);
        Assert.assertEquals(response.getTargetsList().get(0).getName(), "name2");
        Assert.assertEquals(response.getTargetsList().get(0).getDescription(), "description2");
        Assert.assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(10L));
        Assert.assertEquals(response.getTargetsList().get(1).getName(), "name1");
        Assert.assertEquals(response.getTargetsList().get(1).getDescription(), "description1");
        Assert.assertEquals(Optional.ofNullable(response.getTargetsList().get(1).getDeadline()), Optional.of(1L));
    }

    @Test
    public void shouldSearchTargetsByNameWithPaging() {
        Paging paging = new Paging(1, 1);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name2", "description2", 10L));
        targets.add(new Target("name1", "description1", 1L));
        Mockito.when(jpaTargetRepository.findByName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertFalse(response.hasErrors());
        Assert.assertEquals(response.getTargetsList().size(), 1);
        Assert.assertEquals(response.getTargetsList().get(0).getName(), "name2");
        Assert.assertEquals(response.getTargetsList().get(0).getDescription(), "description2");
        Assert.assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(10L));
    }

    @Test
    public void shouldSearchTargetsByNameWithPagingAndOrderingByNameAscending() {
        Ordering ordering = new Ordering("name", "ASCENDING");
        Paging paging = new Paging(1, 1);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name2", "description2", 1L));
        targets.add(new Target("name1", "description1", 10L));
        Mockito.when(jpaTargetRepository.findByName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertFalse(response.hasErrors());
        Assert.assertEquals(response.getTargetsList().size(), 1);
        Assert.assertEquals(response.getTargetsList().get(0).getName(), "name1");
        Assert.assertEquals(response.getTargetsList().get(0).getDescription(), "description1");
        Assert.assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(10L));
    }

    @Test
    public void shouldSearchTargetsByNameWithPagingAndOrderingByNameDescending() {
        Ordering ordering = new Ordering("name", "DESCENDING");
        Paging paging = new Paging(1, 1);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name1", "description1", 10L));
        targets.add(new Target("name2", "description2", 1L));
        Mockito.when(jpaTargetRepository.findByName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertFalse(response.hasErrors());
        Assert.assertEquals(response.getTargetsList().size(), 1);
        Assert.assertEquals(response.getTargetsList().get(0).getName(), "name2");
        Assert.assertEquals(response.getTargetsList().get(0).getDescription(), "description2");
        Assert.assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(1L));
    }

    @Test
    public void shouldSearchTargetsByNameWithPagingAndOrderingByDescriptionDescending() {
        Ordering ordering = new Ordering("description", "DESCENDING");
        Paging paging = new Paging(1, 1);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name1", "description1", 10L));
        targets.add(new Target("name2", "description2", 1L));
        Mockito.when(jpaTargetRepository.findByName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertFalse(response.hasErrors());
        Assert.assertEquals(response.getTargetsList().size(), 1);
        Assert.assertEquals(response.getTargetsList().get(0).getName(), "name2");
        Assert.assertEquals(response.getTargetsList().get(0).getDescription(), "description2");
        Assert.assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(1L));
    }

    @Test
    public void shouldSearchTargetsByNameWithPagingAndOrderingByDescriptionAscending() {
        Ordering ordering = new Ordering("description", "ASCENDING");
        Paging paging = new Paging(1, 1);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name2", "description2", 1L));
        targets.add(new Target("name1", "description1", 10L));
        Mockito.when(jpaTargetRepository.findByName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertFalse(response.hasErrors());
        Assert.assertEquals(response.getTargetsList().size(), 1);
        Assert.assertEquals(response.getTargetsList().get(0).getName(), "name1");
        Assert.assertEquals(response.getTargetsList().get(0).getDescription(), "description1");
        Assert.assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(10L));
    }

    @Test
    public void shouldSearchTargetsByNameWithPagingAndOrderingByDeadlineAscending() {
        Ordering ordering = new Ordering("deadline", "ASCENDING");
        Paging paging = new Paging(1, 1);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name2", "description2", 10L));
        targets.add(new Target("name1", "description1", 1L));
        Mockito.when(jpaTargetRepository.findByName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertFalse(response.hasErrors());
        Assert.assertEquals(response.getTargetsList().size(), 1);
        Assert.assertEquals(response.getTargetsList().get(0).getName(), "name1");
        Assert.assertEquals(response.getTargetsList().get(0).getDescription(), "description1");
        Assert.assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(1L));
    }

    @Test
    public void shouldSearchTargetsByNameWithPagingAndOrderingByDeadlineDescending() {
        Ordering ordering = new Ordering("deadline", "DESCENDING");
        Paging paging = new Paging(1, 1);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("name", ordering, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        targets.add(new Target("name1", "description1", 1L));
        targets.add(new Target("name2", "description2", 10L));
        Mockito.when(jpaTargetRepository.findByName("name")).thenReturn(targets);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertFalse(response.hasErrors());
        Assert.assertEquals(response.getTargetsList().size(), 1);
        Assert.assertEquals(response.getTargetsList().get(0).getName(), "name2");
        Assert.assertEquals(response.getTargetsList().get(0).getDescription(), "description2");
        Assert.assertEquals(Optional.ofNullable(response.getTargetsList().get(0).getDeadline()), Optional.of(10L));
    }

    @Test
    public void invalidSearchTargetByNameRequest_v1() {
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("");
        errors.add(new CoreError("Target name", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertTrue(response.hasErrors());
        Assert.assertEquals(response.getErrorList().size(), 1);
        Assert.assertEquals(response.getErrorList().get(0).getField(), "Target name");
        Assert.assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v2() {
        SearchTargetByNameRequest request = new SearchTargetByNameRequest(null);
        errors.add(new CoreError("Target name", "must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertTrue(response.hasErrors());
        Assert.assertEquals(response.getErrorList().size(), 1);
        Assert.assertEquals(response.getErrorList().get(0).getField(), "Target name");
        Assert.assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v3() {
        Ordering ordering = new Ordering(null, "ASCENDING");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", ordering);
        errors.add(new CoreError("Order by", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertTrue(response.hasErrors());
        Assert.assertEquals(response.getErrorList().size(), 1);
        Assert.assertEquals(response.getErrorList().get(0).getField(), "Order by");
        Assert.assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v4() {
        Ordering ordering = new Ordering("", "ASCENDING");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", ordering);
        errors.add(new CoreError("Order by", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertTrue(response.hasErrors());
        Assert.assertEquals(response.getErrorList().size(), 1);
        Assert.assertEquals(response.getErrorList().get(0).getField(), "Order by");
        Assert.assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v5() {
        Ordering ordering = new Ordering("sad", "ASCENDING");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", ordering);
        errors.add(new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertTrue(response.hasErrors());
        Assert.assertEquals(response.getErrorList().size(), 1);
        Assert.assertEquals(response.getErrorList().get(0).getField(), "Order by");
        Assert.assertEquals(response.getErrorList().get(0).getMessage(), "must contain NAME, DESCRIPTION or DEADLINE only!");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v6() {
        Ordering ordering = new Ordering("sad", null);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", ordering);
        errors.add(new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
        errors.add(new CoreError("Order direction", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertTrue(response.hasErrors());
        Assert.assertEquals(response.getErrorList().size(), 2);
        Assert.assertEquals(response.getErrorList().get(0).getField(), "Order by");
        Assert.assertEquals(response.getErrorList().get(0).getMessage(), "must contain NAME, DESCRIPTION or DEADLINE only!");
        Assert.assertEquals(response.getErrorList().get(1).getField(), "Order direction");
        Assert.assertEquals(response.getErrorList().get(1).getMessage(), "must not be empty");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v7() {
        Ordering ordering = new Ordering("sad", "");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", ordering);
        errors.add(new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
        errors.add(new CoreError("Order direction", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertTrue(response.hasErrors());
        Assert.assertEquals(response.getErrorList().size(), 2);
        Assert.assertEquals(response.getErrorList().get(0).getField(), "Order by");
        Assert.assertEquals(response.getErrorList().get(0).getMessage(), "must contain NAME, DESCRIPTION or DEADLINE only!");
        Assert.assertEquals(response.getErrorList().get(1).getField(), "Order direction");
        Assert.assertEquals(response.getErrorList().get(1).getMessage(), "must not be empty");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v8() {
        Ordering ordering = new Ordering("sad", "dsv");
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", ordering);
        errors.add(new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
        errors.add(new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertTrue(response.hasErrors());
        Assert.assertEquals(response.getErrorList().size(), 2);
        Assert.assertEquals(response.getErrorList().get(0).getField(), "Order by");
        Assert.assertEquals(response.getErrorList().get(0).getMessage(), "must contain NAME, DESCRIPTION or DEADLINE only!");
        Assert.assertEquals(response.getErrorList().get(1).getField(), "Order direction");
        Assert.assertEquals(response.getErrorList().get(1).getMessage(), "must contain ASCENDING or DESCENDING only!");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v9() {
        Ordering ordering = new Ordering("sad", "dsv");
        Paging paging = new Paging(-2, 1);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", ordering, paging);
        errors.add(new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
        errors.add(new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
        errors.add(new CoreError("Page number", "must be greater then 0!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertTrue(response.hasErrors());
        Assert.assertEquals(response.getErrorList().size(), 3);
        Assert.assertEquals(response.getErrorList().get(0).getField(), "Order by");
        Assert.assertEquals(response.getErrorList().get(0).getMessage(), "must contain NAME, DESCRIPTION or DEADLINE only!");
        Assert.assertEquals(response.getErrorList().get(1).getField(), "Order direction");
        Assert.assertEquals(response.getErrorList().get(1).getMessage(), "must contain ASCENDING or DESCENDING only!");
        Assert.assertEquals(response.getErrorList().get(2).getField(), "Page number");
        Assert.assertEquals(response.getErrorList().get(2).getMessage(), "must be greater then 0!");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v10() {
        Ordering ordering = new Ordering("sad", "dsv");
        Paging paging = new Paging(null, 1);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", ordering, paging);
        errors.add(new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
        errors.add(new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
        errors.add(new CoreError("Page number", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertTrue(response.hasErrors());
        Assert.assertEquals(response.getErrorList().size(), 3);
        Assert.assertEquals(response.getErrorList().get(0).getField(), "Order by");
        Assert.assertEquals(response.getErrorList().get(0).getMessage(), "must contain NAME, DESCRIPTION or DEADLINE only!");
        Assert.assertEquals(response.getErrorList().get(1).getField(), "Order direction");
        Assert.assertEquals(response.getErrorList().get(1).getMessage(), "must contain ASCENDING or DESCENDING only!");
        Assert.assertEquals(response.getErrorList().get(2).getField(), "Page number");
        Assert.assertEquals(response.getErrorList().get(2).getMessage(), "must not be empty");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v11() {
        Ordering ordering = new Ordering("sad", "dsv");
        Paging paging = new Paging(null, -1);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", ordering, paging);
        errors.add(new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
        errors.add(new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
        errors.add(new CoreError("Page number", "must not be empty"));
        errors.add(new CoreError("Page size", "must be greater then 0!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertTrue(response.hasErrors());
        Assert.assertEquals(response.getErrorList().size(), 4);
        Assert.assertEquals(response.getErrorList().get(0).getField(), "Order by");
        Assert.assertEquals(response.getErrorList().get(0).getMessage(), "must contain NAME, DESCRIPTION or DEADLINE only!");
        Assert.assertEquals(response.getErrorList().get(1).getField(), "Order direction");
        Assert.assertEquals(response.getErrorList().get(1).getMessage(), "must contain ASCENDING or DESCENDING only!");
        Assert.assertEquals(response.getErrorList().get(2).getField(), "Page number");
        Assert.assertEquals(response.getErrorList().get(2).getMessage(), "must not be empty");
        Assert.assertEquals(response.getErrorList().get(3).getField(), "Page size");
        Assert.assertEquals(response.getErrorList().get(3).getMessage(), "must be greater then 0!");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v12() {
        Ordering ordering = new Ordering("sad", "dsv");
        Paging paging = new Paging(null, null);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", ordering, paging);
        errors.add(new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
        errors.add(new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
        errors.add(new CoreError("Page number", "must not be empty"));
        errors.add(new CoreError("Page size", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertTrue(response.hasErrors());
        Assert.assertEquals(response.getErrorList().size(), 4);
        Assert.assertEquals(response.getErrorList().get(0).getField(), "Order by");
        Assert.assertEquals(response.getErrorList().get(0).getMessage(), "must contain NAME, DESCRIPTION or DEADLINE only!");
        Assert.assertEquals(response.getErrorList().get(1).getField(), "Order direction");
        Assert.assertEquals(response.getErrorList().get(1).getMessage(), "must contain ASCENDING or DESCENDING only!");
        Assert.assertEquals(response.getErrorList().get(2).getField(), "Page number");
        Assert.assertEquals(response.getErrorList().get(2).getMessage(), "must not be empty");
        Assert.assertEquals(response.getErrorList().get(3).getField(), "Page size");
        Assert.assertEquals(response.getErrorList().get(3).getMessage(), "must not be empty");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v13() {
        Paging paging = new Paging(1, null);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", paging);
        errors.add(new CoreError("Page size", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertTrue(response.hasErrors());
        Assert.assertEquals(response.getErrorList().size(), 1);
        Assert.assertEquals(response.getErrorList().get(0).getField(), "Page size");
        Assert.assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v14() {
        Paging paging = new Paging(1, -2);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", paging);
        errors.add(new CoreError("Page size", "must be greater then 0!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertTrue(response.hasErrors());
        Assert.assertEquals(response.getErrorList().size(), 1);
        Assert.assertEquals(response.getErrorList().get(0).getField(), "Page size");
        Assert.assertEquals(response.getErrorList().get(0).getMessage(), "must be greater then 0!");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v15() {
        Paging paging = new Paging(-6, 1);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", paging);
        errors.add(new CoreError("Page number", "must be greater then 0!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertTrue(response.hasErrors());
        Assert.assertEquals(response.getErrorList().size(), 1);
        Assert.assertEquals(response.getErrorList().get(0).getField(), "Page number");
        Assert.assertEquals(response.getErrorList().get(0).getMessage(), "must be greater then 0!");
    }

    @Test
    public void invalidSearchTargetByNameRequest_v16() {
        Paging paging = new Paging(null, 1);
        SearchTargetByNameRequest request = new SearchTargetByNameRequest("vsd", paging);
        errors.add(new CoreError("Page number", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchTargetByNameResponse response = service.execute(request);
        Assert.assertTrue(response.hasErrors());
        Assert.assertEquals(response.getErrorList().size(), 1);
        Assert.assertEquals(response.getErrorList().get(0).getField(), "Page number");
        Assert.assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty");
    }
}