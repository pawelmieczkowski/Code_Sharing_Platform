package platform;


import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CodeSnippetRepository extends CrudRepository<CodeSnippet, Long> {
    void deleteById(UUID id);
}
