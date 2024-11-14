package edu.ICET.entity;

import edu.ICET.dto.DietaryInfo;
import edu.ICET.dto.Login;
import edu.ICET.dto.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="UserPlan")
public class UserWithPlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(targetEntity = UserEntity.class,cascade = CascadeType.ALL) // or CascadeType.ALL
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
    @OneToOne(targetEntity = LoginEntity.class,cascade = CascadeType.ALL) // or CascadeType.ALL
    @JoinColumn(name = "login_id", referencedColumnName = "id")
    private LoginEntity login;
    @OneToOne(targetEntity = DietaryInfoEntity.class,cascade = {CascadeType.ALL,CascadeType.REMOVE})
    @JoinColumn(name = "dietary_info_id", referencedColumnName = "id")
    private DietaryInfoEntity dietaryInfo;
    @OneToOne(targetEntity = DietPlanEntity.class,cascade = {CascadeType.ALL,CascadeType.REMOVE})
    @JoinColumn(name = "plan_id", referencedColumnName = "id")
    private DietPlanEntity dietPlan;
}
