package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


@RestController
public class CodeSharingAPIController {
    @Autowired
    private CodeSnippetService codeSnippetService;

    @GetMapping(path = "/api/code/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CodeSnippet getCode(@PathVariable String id) {
        CodeSnippet cs = codeSnippetService.getById(id);
        if (cs == null) {
            throw new SnippetNotFoundException("id: " + id);
        }
        return cs;

    }

    @GetMapping(path = "/api/code/latest", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CodeSnippet> getLatest() {
        return codeSnippetService.latest();
        //return DataBase.latest();
    }

    @PostMapping(path = "/api/code/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> setCode(@RequestBody CodeSnippet codeSnippet) {
        System.out.println(codeSnippet.getCode() + " <-CODE-SNIPPET");
        System.out.println(codeSnippet.getTime() + " time");
        System.out.println(codeSnippet.getViews() + " views");
        System.out.println("code added");
        System.out.println("new codeSnippets Id: " + codeSnippet.getId());
        Map<String, String> map = new TreeMap<>();
        CodeSnippet cd = codeSnippetService.save(codeSnippet);
        map.put("id", cd.getId().toString());
        return map;
    }
}
