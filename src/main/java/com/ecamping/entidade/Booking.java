/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamping.entidade;

import com.ecamping.validator.ValidateTent;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ramon
 */
@Entity
@Table(name = "tb_booking")
@Access(AccessType.FIELD)
@NamedQueries(
        {
            @NamedQuery(
                    name = Booking.BOOKING_POR_USER,
                    query = "SELECT b FROM Booking b WHERE b.user.name LIKE ?1"
            ),
            @NamedQuery(
                    name = Booking.BOOKING_POR_NOME_CAMPING,
                    query = "SELECT b FROM Booking b WHERE b.camping.name LIKE ?1 "
            ),
            @NamedQuery(
                    name = Booking.BOOKING_POR_DATA,
                    query = "SELECT b FROM Booking  b WHERE b.bookingDate BETWEEN ?1 AND ?2"
            ),
            @NamedQuery(
                    name = Booking.BOOKING_POR_ID,
                    query = "SELECT b FROM Booking b WHERE b.id LIKE ?1"
            )
        }
)
public class Booking implements Serializable {
    public static final String BOOKING_POR_USER = "BookingPorUser";
    public static final String BOOKING_POR_NOME_CAMPING = "BookingPorNomeCamping";
    public static final String BOOKING_POR_DATA = "BookingPorData";
    public static final String BOOKING_POR_ID = "BookingPorId";
    
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Future(message="{invalid.bookingDate}")
    @Column(name = "DT_BOOKINGDATE")
    @Temporal(TemporalType.DATE)
    private Date bookingDate;
    
    @ValidateTent
    @Column(name = "TXT_TENT", nullable = true)
    private String tent;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "ID_CAMPING", referencedColumnName = "ID", nullable = false)
    private Camping camping;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Camping getCamping() {
        return camping;
    }

    public void setCamping(Camping camping) {
        this.camping = camping;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTent() {
        return tent;
    }

    public void setTent(String tent) {
        this.tent = tent;
    }
/*
    void setBookingDate(Calendar c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   */

}
