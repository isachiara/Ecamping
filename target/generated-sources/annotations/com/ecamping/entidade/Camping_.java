package com.ecamping.entidade;

import com.ecamping.entidade.Address;
import com.ecamping.entidade.Booking;
import com.ecamping.entidade.Comment;
import com.ecamping.entidade.Rating;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-22T21:17:17")
@StaticMetamodel(Camping.class)
public class Camping_ { 

    public static volatile ListAttribute<Camping, Booking> booking;
    public static volatile SingularAttribute<Camping, Address> address;
    public static volatile SingularAttribute<Camping, String> phone;
    public static volatile SingularAttribute<Camping, String> name;
    public static volatile ListAttribute<Camping, Rating> rating;
    public static volatile ListAttribute<Camping, Comment> comment;
    public static volatile SingularAttribute<Camping, Long> id;
    public static volatile SingularAttribute<Camping, String> info;

}