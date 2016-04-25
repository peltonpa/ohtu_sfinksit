package sfinksit.repository;

import java.util.List;
import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sfinksit.domain.Reference;


public interface ReferenceRepository extends JpaRepository<Reference, Long> {    
    @Query("select u from Reference u where u.bibtexKey = ?1")
    List<Reference> findExistingBibtexKey(String bibtexKey);
    
    @Query("SELECT u from Reference u LEFT JOIN u.article a LEFT JOIN u.book b LEFT JOIN u.conference c WHERE u.bibtexKey = ?1 OR u.author = ?1 OR u.publisher = ?1 OR u.title = ?1 OR u.article.journal = ?1 OR u.article.address = ?1 OR u.book.series = ?1 OR u.book.address = ?1 OR u.book.edition = ?1 OR u.book.month = ?1 OR u.book.note = ?1 OR u.conference.booktitle = ?1 OR u.conference.editor = ?1 OR u.conference.series = ?1 OR u.conference.address = ?1 OR u.conference.organization = ?1 OR u.conference.note = ?1")
    List<Reference> findSearchTermFromAll(String searchTerm); 
}