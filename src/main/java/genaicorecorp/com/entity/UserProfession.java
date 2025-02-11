package genaicorecorp.com.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_profession", schema = "ecom")
public class UserProfession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profession_id")
    private Long professionId;

    @Column(name = "profession_name", nullable = false, unique = true)
    private String professionName;

	public Long getProfessionId() {
		return professionId;
	}

	public void setProfessionId(Long professionId) {
		this.professionId = professionId;
	}

	public String getProfessionName() {
		return professionName;
	}

	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}

	@Override
	public String toString() {
		return "UserProfession [professionId=" + professionId + ", professionName=" + professionName + "]";
	}

	public UserProfession(Long professionId, String professionName) {
		super();
		this.professionId = professionId;
		this.professionName = professionName;
	}

	public UserProfession() {
		super();
	}
}