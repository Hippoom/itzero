package itzero.http

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.*
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import static org.springframework.restdocs.payload.PayloadDocumentation.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner)
@WebMvcTest
        ([IndexRestController])
@AutoConfigureRestDocs(outputDir = "build/generated-snippets")
class IndexRestControllerTest {

    @Autowired
    private MockMvc mockMvc

    @Test
    void list_rels() {

        // @formatter:off
        this.mockMvc.perform(
                    get("/rel/index")
                )
	            .andExpect(status().isOk())
                .andDo(print())
	            .andDo(
                    document("index",
                        responseFields(
                            fieldWithPath("content").ignored(),
                            subsectionWithPath("_links").ignored()//validate by links() block
                        ),
                        links(halLinks(),
                            linkWithRel("self").description("link to refresh index")
                        )
                    )
                )
        // @formatter:on
    }
}
