package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class CodeSharingWebController {

    @Autowired
    private CodeSnippetService codeSnippetService;

    @GetMapping(value = "/code/new", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String newCode() {
        return "<html>\n" +
                "<head>\n" +
                "    <title>Create</title>\n" +
                "<link rel=\"stylesheet\" href=/css/code.css>" +
                "<script>function send() {\n" +
"    let views = parseInt(document.getElementById(\"views_restriction\").value);\n" +
                "    if(views===0 || isNaN(views)){\n" +
                "        views = 0;\n" +
                "    }\n" +
                "\n" +
                "    let object = {\n" +
                "        \"code\": document.getElementById(\"code_snippet\").value,\n" +
                "        \"time\": parseInt(document.getElementById(\"time_restriction\").value),\n" +
                "        \"views\": views\n" +
                "    };"+
                "console.log(object);" +
                "    let json = JSON.stringify(object);\n" +
                "\n" +
                "    let xhr = new XMLHttpRequest();\n" +
                "    xhr.open(\"POST\", '/api/code/new', false)\n" +
                "    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');\n" +
                "    xhr.send(json);\n" +
                "\n" +
                "    if (xhr.status == 200) {\n" +
                "        alert(\"Success!\");\n" +
                "    }\n" +
                "}</script>" +
                "</head>\n" +
                "<body>\n" +
//                "<form action=\"/api/code/new\" method=\"POST\">\n" +
                "<textarea id=\"code_snippet\" name=\"code\" rows=\"10\" cols=\"100\">" +
                "//Write your code here" +
                "</textarea>" +
                "<br>" +
                "<label for=\"time_restriction\">Time restriction:</label>" +
                "<input id=\"time_restriction\" type=\"text\"/>" +
                "<br>" +
                "<label for=\"views_restriction\">Views restriction:</label>" +
                "<input id=\"views_restriction\" type=\"text\"/>" +
                "<br>" +
                "<button id=\"send_snippet\" type=\"submit\" onclick=\"send()\">Submit</button>" +
//                "</form>" +
                "</body>\n" +
                "</html>";
    }

    @GetMapping(path = "/code/latest", produces = MediaType.TEXT_HTML_VALUE)
    public String getLatest(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("codeSnippets", codeSnippetService.latest());
        return "getCodeNTemplate";
    }

    @GetMapping(path = "/code/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String getCodeById(@ModelAttribute("model") ModelMap model, @PathVariable String id) {
        CodeSnippet cs = codeSnippetService.getById(id);
        if (cs == null) {
            throw new SnippetNotFoundException("id: " + id);
        }
        model.addAttribute("codeSnippet", cs);
        return "codeById";
    }
}
