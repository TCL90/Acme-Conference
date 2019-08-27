
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
	
	@Query("select m from Message m where m.sender = ?1")
	Collection<Message> findByActor(Actor actor);
//
//	@Query("select m from Message m where m.sender = ?1 or m.recipient = ?1")
//	Collection<Message> findByMy(Actor actor);

//	@Query("select m.recipients from Message m where m.id =?1")
//	public Collection<Actor> getRecipientsWorking(int idmsj);
	
//	@Query("select * from Message m where m.sender = ?1")
//	public Collection<Message> getMessageFrom(Actor a);
}
