package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;

@Entity
public class Address extends Model {

    @Id
    public long Id;

    @Constraints.Required
    public String street;
}
