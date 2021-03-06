package br.com.infosys.moviedb.domain.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.infosys.moviedb.domain.enums.GenreEnum;

/**
 * Movie entity.
 * 
 * @author vitor191291@gmail.com
 *
 */
@Entity
@Table(name = "MOVIE")
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MOVIE_ID", nullable = false, unique = true)
	private Long idMovie;

	@Column(name = "TITLE", nullable = false, length = 100)
	private String title;

	@ManyToOne
	@JoinColumn(name = "DIRECTOR_ID", referencedColumnName = "DIRECTOR_ID")
	private Director director;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "MOVIE_WRITERS",
    		   joinColumns = @JoinColumn(name = "MOVIE_ID") , 
    		   inverseJoinColumns = @JoinColumn(name = "WRITER_ID") )
	private Set<Writer> writers;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "MOVIE_ACTORS",
	           joinColumns = @JoinColumn(name = "MOVIE_ID") , 
	           inverseJoinColumns = @JoinColumn(name = "ACTOR_ID") )
	private Set<Actor> cast;

	@Enumerated(EnumType.STRING)
	@Column(name = "GENRE", nullable = false)
	private GenreEnum genre;

	@Column(name = "PLOT_SUMMARY", nullable = false, length = 500)
	private String plotSummary;

	@Column(name = "COUNTRY", nullable = false, length = 50)
	private String country;

	@Column(name = "LANGUAGE", nullable = false, length = 50)
	private String language;

	@Temporal(TemporalType.DATE)
	@Column(name = "RELEASE_DATE", nullable = false)
	private Date releaseDate;

	@Version
	private Integer version;

	public Movie() {
	}

	public Long getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(Long idMovie) {
		this.idMovie = idMovie;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public Set<Writer> getWriters() {
		return writers;
	}
	
	public void setWriters(Set<Writer> writers) {
		this.writers = writers;
	}

	public Set<Actor> getCast() {
		return cast;
	}

	public void setCast(Set<Actor> cast) {
		this.cast = cast;
	}

	public GenreEnum getGenre() {
		return genre;
	}

	public void setGenre(GenreEnum genre) {
		this.genre = genre;
	}

	public String getPlotSummary() {
		return plotSummary;
	}

	public void setPlotSummary(String plotSummary) {
		this.plotSummary = plotSummary;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Integer getVersion() {
		return version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMovie == null) ? 0 : idMovie.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Movie))
			return false;

		final Movie other = (Movie) obj;
		if (idMovie == null) {
			if (other.idMovie != null)
				return false;
		} else if (!idMovie.equals(other.idMovie))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}

}
