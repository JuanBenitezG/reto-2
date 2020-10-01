
package model.logic;


import model.data_structures.*;


public class pelicula implements Comparable<pelicula>  {

	public String budget,genres,imdb_id,original_lan,original_title,overview,popularity,production_companies,production_countries,release_date,revenue,runtime,spoken_language,status,tagline,title;                              
	public int Id, vote_count,production_companies_number,production_countries_number,spoken_languages_number;
	public String actor_1,  actor_2,  actor_3,  actor_4, actor_5,Director;  
	public int getVote_count() {
		return vote_count;
	}

	public String getActorN(int n){
		if(n==0){
			return actor_1;
		}
		if(n==1){
			return actor_2;
		}
		if(n==2){
			return actor_3;
		}
		if(n==3){
			return actor_4;
		}
		if(n==4){
			return actor_5;
		}
		return null;
	}
	public void setVote_count(int vote_count) {
		this.vote_count = vote_count;
	}

	public String getActor_1() {
		return actor_1;
	}

	public void setActor_1(String actor_1) {
		this.actor_1 = actor_1;
	}

	public String getActor_2() {
		return actor_2;
	}

	public void setActor_2(String actor_2) {
		this.actor_2 = actor_2;
	}

	public String getActor_3() {
		return actor_3;
	}

	public void setActor_3(String actor_3) {
		this.actor_3 = actor_3;
	}

	public String getActor_4() {
		return actor_4;
	}

	public void setActor_4(String actor_4) {
		this.actor_4 = actor_4;
	}

	public String getActor_5() {
		return actor_5;
	}

	public void setActor_5(String actor_5) {
		this.actor_5 = actor_5;
	}

	public String getDirector() {
		return Director;
	}

	public void setDirector(String director) {
		Director = director;
	}

	public void setVote_average(float vote_average) {
		this.vote_average = vote_average;
	}
	public float vote_average;


	public pelicula(int id, String budget, String genres, String imdb_id, String original_lan, String original_title,
			String overview, String popularity, String production_companies, String production_countries,
			String release_date, String revenue, String runtime, String spoken_language, String status, String tagline,
			String title, float vote_average,int vote_count, int production_companies_number, int production_countries_number,
			int spoken_languages_number, String actor_1, String actor_2, String actor_3, String actor_4 ,String actor_5,String Director ) {
		super();
		Id = id;
		this.budget = budget;
		this.genres = genres;
		this.imdb_id = imdb_id;
		this.original_lan = original_lan;
		this.original_title = original_title;
		this.overview = overview;
		this.popularity = popularity;
		this.production_companies = production_companies;
		this.production_countries = production_countries;
		this.release_date = release_date;
		this.revenue = revenue;
		this.runtime = runtime;
		this.spoken_language = spoken_language;
		this.status = status;
		this.tagline = tagline;
		this.title = title;
		this.vote_average = vote_average;
		this.vote_count= vote_count;
		this.production_companies_number = production_companies_number;
		this.production_countries_number = production_countries_number;
		this.spoken_languages_number = spoken_languages_number;
		this.actor_1=actor_1;
		this.actor_2=actor_2;
		this.actor_3=actor_3;
		this.actor_4=actor_4;
		this.actor_5=actor_5;
		this.Director=Director;




	}

	public int getId() {
		return Id;
	}



	public void setId(int id) {
		Id = id;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public String getGenres() {
		return genres;
	}
	public void setGenres(String genres) {
		this.genres = genres;
	}
	public String getImdb_id() {
		return imdb_id;
	}
	public void setImdb_id(String imdb_id) {
		this.imdb_id = imdb_id;
	}
	public String getOriginal_lan() {
		return original_lan;
	}
	public void setOriginal_lan(String original_lan) {
		this.original_lan = original_lan;
	}
	public String getOriginal_title() {
		return original_title;
	}
	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getPopularity() {
		return popularity;
	}
	public void setPopularity(String popularity) {
		this.popularity = popularity;
	}
	public String getProduction_companies() {
		return production_companies;
	}
	public void setProduction_companies(String production_companies) {
		this.production_companies = production_companies;
	}
	public String getProduction_countries() {
		return production_countries;
	}
	public void setProduction_countries(String production_countries) {
		this.production_countries = production_countries;
	}
	public String getRelease_date() {
		return release_date;
	}
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
	public String getRevenue() {
		return revenue;
	}
	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}
	public String getRuntime() {
		return runtime;
	}
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	public String getSpoken_language() {
		return spoken_language;
	}
	public void setSpoken_language(String spoken_language) {
		this.spoken_language = spoken_language;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTagline() {
		return tagline;
	}
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Float getVote_average() {
		return vote_average;
	}
	public void setVote_average(int vote_average) {
		this.vote_average = vote_average;
	}
	public int getProduction_companies_number() {
		return production_companies_number;
	}
	public void setProduction_companies_number(int production_companies_number) {
		this.production_companies_number = production_companies_number;
	}
	public int getProduction_countries_number() {
		return production_countries_number;
	}
	public void setProduction_countries_number(int production_countries_number) {
		this.production_countries_number = production_countries_number;
	}
	public int getSpoken_languages_number() {
		return spoken_languages_number;
	}
	public void setSpoken_languages_number(int spoken_languages_number) {
		this.spoken_languages_number = spoken_languages_number;
	}
	// returna 0 si son iguales, returna 1 si la pelicula es mayor que la dada enj el parametro y -1 si la del parametro es mayor 
	@Override
	public int compareTo(pelicula o) {

		if(this.getVote_average()==o.getVote_average()){
			return 0;
		}
		else if(this.getVote_average()>o.getVote_average()){
			return 1;
		}
		else{
			return -1;
		}

	}



}