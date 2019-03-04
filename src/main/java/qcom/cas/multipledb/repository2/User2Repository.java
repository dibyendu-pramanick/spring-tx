package qcom.cas.multipledb.repository2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import qcom.cas.multipledb.entity2.User2;

@Repository
public interface User2Repository extends JpaRepository<User2, Integer>{
	
	@Modifying
	@Query("UPDATE User2 SET firstName = :firstName WHERE id= :id")
	void updateFirstName(@Param("id") int id, @Param("firstName") String firstName);
	
	@Modifying
	@Query("UPDATE User2 SET lastName = :lastName WHERE id= :id")
	void updateLastName(@Param("id") int id, @Param("lastName") String lastName);

}
