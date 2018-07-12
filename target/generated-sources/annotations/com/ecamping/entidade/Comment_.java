package com.ecamping.entidade;

import com.ecamping.entidade.Camping;
import com.ecamping.entidade.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-12T00:07:49")
@StaticMetamodel(Comment.class)
public class Comment_ extends Feedback_ {

    public static volatile SingularAttribute<Comment, Camping> camping;
    public static volatile SingularAttribute<Comment, String> message;
    public static volatile SingularAttribute<Comment, User> user;

}