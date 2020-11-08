package platform;

import org.apache.tomcat.jni.Local;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class CodeSnippetService {

    @Autowired
    private CodeSnippetRepository codeSnippetRepository;

    public CodeSnippet save(CodeSnippet codeSnippet) {
        return codeSnippetRepository.save(codeSnippet);
    }

    public List<CodeSnippet> findAll() {
        return (List<CodeSnippet>) codeSnippetRepository.findAll();
    }

    public List<CodeSnippet> latest() {
        List<CodeSnippet> dataBaseArray = (List<CodeSnippet>) codeSnippetRepository.findAll();
        int size = dataBaseArray.size() > 9 ? 10 : dataBaseArray.size();
        //List<CodeSnippet> temp = new ArrayList<>(dataBaseArray.subList(dataBaseArray.size() - size, dataBaseArray.size()));
        List<CodeSnippet> temp = new ArrayList<>();
        for (int i = 0; i < dataBaseArray.size(); i++) {
            if (dataBaseArray.get(dataBaseArray.size() - 1 - i).getTime() < 1 && dataBaseArray.get(dataBaseArray.size() - 1 - i).getViews() < 1) {
                temp.add(dataBaseArray.get(dataBaseArray.size() - 1 - i));
                if (temp.size() >= size) {
                    break;
                }
            }
        }
//        Collections(temp);
        return temp;
        //TODO sorting?
    }

    @Transactional
    public CodeSnippet getById(String id) {
        List<CodeSnippet> dataBaseArray = (List<CodeSnippet>) codeSnippetRepository.findAll();
        for (CodeSnippet codeSnippet : dataBaseArray) {
            if (codeSnippet.getId().toString().replace("-", "").equals(id.replace("-", ""))) {
                if (codeSnippet.getTime() > 0) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//                    LocalDateTime dbTime = LocalDateTime.parse(codeSnippet.getDate(), formatter);

                    LocalDateTime currentTime = LocalDateTime.now();

                    Duration duration = Duration.between(codeSnippet.getLastTimeCheck(), currentTime);
                    if (duration.getSeconds() > codeSnippet.getTime()) {
                        codeSnippetRepository.deleteById(codeSnippet.getId());
                        return null;
                    } else {
                        codeSnippet.setTime(codeSnippet.getTime() - (int) duration.getSeconds());
                        codeSnippet.setLastTimeCheck(LocalDateTime.now());
                        codeSnippetRepository.save(codeSnippet);
                    }
                }
                if (codeSnippet.getViews() > 0) {
                    System.out.println("DELETING :" + dataBaseArray.size());
                    codeSnippet.setViews(codeSnippet.getViews() - 1);
                    codeSnippetRepository.save(codeSnippet);
                    if (codeSnippet.getViews() == 0) {
                        codeSnippetRepository.deleteById(codeSnippet.getId());
                    }
                }
                return codeSnippet;
            }
        }
        return null;
    }


}
