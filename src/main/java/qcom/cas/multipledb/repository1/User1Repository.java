package qcom.cas.multipledb.repository1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import qcom.cas.multipledb.entity1.User1;

@Repository
public interface User1Repository extends JpaRepository<User1, Integer> {
	
	@Modifying
	@Query("UPDATE User1 SET firstName = :firstName WHERE id= :id")
	void updateFirstName(@Param("id") int id, @Param("firstName") String firstName);
	
	@Modifying
	@Query("UPDATE User1 SET lastName = :lastName WHERE id= :id")
	void updateLastName(@Param("id") int id, @Param("lastName") String lastName);

}
