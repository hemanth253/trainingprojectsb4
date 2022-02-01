package in.hemanth.trainingprojectsb4.department;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @Before
    public void setUp() {
        department=new Department(1,"hr","ny");
    }

    @Test
    public void addRecord() throws Exception {
        Department inputDepartment = Department.builder()
                .rediskey(1)
                .name("hr")
                .location("ny")
                .build();

        mockMvc.perform(post("/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"rediskey\":1,\n" +
                                "    \"name\":\"hr\",\n" +
                                "    \"location\":\"ny\"\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(departmentService, times(1)).addRecord(inputDepartment);
    }

    @Test
    public void retrieveRecordById() throws Exception {
        Mockito.when(departmentService.retrieveRecordsById(1))
                .thenReturn(department);

        mockMvc.perform(get("/department/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.rediskey").
                        value(department.getRediskey()));
    }
}