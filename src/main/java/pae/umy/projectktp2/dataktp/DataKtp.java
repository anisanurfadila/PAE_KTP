/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pae.umy.projectktp2.dataktp;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "data_ktp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DataKtp.findAll", query = "SELECT d FROM DataKtp d"),
    @NamedQuery(name = "DataKtp.findById", query = "SELECT d FROM DataKtp d WHERE d.id = :id"),
    @NamedQuery(name = "DataKtp.findByNama", query = "SELECT d FROM DataKtp d WHERE d.nama = :nama"),
    @NamedQuery(name = "DataKtp.findByTanggal", query = "SELECT d FROM DataKtp d WHERE d.tanggal = :tanggal")})
public class DataKtp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nama")
    private String nama;
    @Basic(optional = false)
    @Column(name = "tanggal")
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    @Basic(optional = false)
    @Lob
    @Column(name = "foto")
    private String foto;

    public DataKtp() {
    }

    public DataKtp(Integer id) {
        this.id = id;
    }

    public DataKtp(Integer id, String nama, Date tanggal, String foto) {
        this.id = id;
        this.nama = nama;
        this.tanggal = tanggal;
        this.foto = foto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataKtp)) {
            return false;
        }
        DataKtp other = (DataKtp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pae.umy.projectktp2.dataktp.DataKtp[ id=" + id + " ]";
    }
    
}
