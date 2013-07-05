package net.nueca.imonggo.objects;

import org.json.JSONException;
import org.json.JSONObject;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Below are the field that can be processed.
 * <ol>
 * 	<li>id* - <i>Unique ID</i></li>
 * 	<li>code* - <i>Unique customer code, system assigned based on format</i></li>
 * 	<li>alternate_code - <i>User assign alternate customer code</i></li>
 * 	<li>first_name - <i>First name</i></li>
 * 	<li>last_name - <i>Last name</i></li>
 * 	<li>name - <i>Full name, concatenation of first and last name (max 100)</i></li>
 * 	<li>company_name - <i>Company name</i></li>
 * 	<li>tin - <i>Tax Identification Number</i></li>
 * 	<li>tax_exempt - <i>Tax exempt flag (default is false), set to true if customer is tax exempt</i></li>
 * 	<li>street - <i>Street</i></li>
 * 	<li>city - <i>City</i></li>
 * 	<li>state - <i>State (Two Letter State code, only applicable for USA)</i></li>
 * 	<li>zipcode - <i>Zip code</i></li>
 * 	<li>country - <i>Country Code (Two letter country code. Refer to this table for complete list)</i></li>
 * 	<li>telephone - <i>Telephone number</i></li>
 * 	<li>fax - <i>Fax number</i></li>
 * 	<li>mobile - <i>Mobile number</i></li>
 * 	<li>email - <i>Email address</i></li>
 * 	<li>remark - <i>Remark</i></li>
 * 	<li>customer_type_id* - <i>Customer Membership type id</i></li>
 * 	<li>customer_type_name* - <i>Customer Membership Type name</i></li>
 * 	<li>discount_text* - <i>Discount in percent based on membership type (e.g. "5%")</i></li>
 * 	<li>available_points* - <i>Reward points available</i></li>
 * 	<li>birthdate - <i>Birthday in YYYY-MM-DD format</i></li>
 * 	<li>status* - <i>D if deleted, otherwise NULL</i></li>
 * 	<li>point_to_amount_ratio - <i>point_to_amount_ratio</i></li>
 * 	<li>utc_created_at - <i>utc_created_at</i></li>
 * 	<li>utc_updated_at - <i>utc_updated_at</i></li>
 * 	<li>birthday - <i>birthday</i></li>
 * </ol>
 * 
 */
@DatabaseTable
public class Customer {
	
	@DatabaseField
	private int id, point_to_amount_ratio;
	private String code, alternate_code, first_name, last_name, name, company_name, tin, street, city, state, 
			zipcode, country, telephone, fax, mobile, email, remark, customer_type_id, customer_type_name, discount_text, 
			available_points, birthdate, status, utc_created_at, utc_updated_at, birthday;
	@DatabaseField
	private boolean tax_exempt;
	
	public Customer() { }
	
	public Customer(JSONObject customerObj) {
		processCustomerObject(customerObj);
	}
	
	public void processCustomerObject(JSONObject jsonObj) {
		try {
			setId(jsonObj.getInt("id"));
			setPoint_to_amount_ratio(jsonObj.getInt("point_to_amount_ratio"));
			
			setCode(jsonObj.getString("code"));
			setAlternate_code(jsonObj.getString("alternative_code"));
			setFirst_name(jsonObj.getString("first_name"));
			setLast_name(jsonObj.getString("last_name"));
			setName(jsonObj.getString("name"));
			setCompany_name(jsonObj.getString("company_name"));
			setTin(jsonObj.getString("tin"));
			setStreet(jsonObj.getString("street"));
			setCity(jsonObj.getString("city"));
			setState(jsonObj.getString("state"));
			setZipcode(jsonObj.getString("zipcode"));
			setCountry(jsonObj.getString("country"));
			setTelephone(jsonObj.getString("telephone"));
			setFax(jsonObj.getString("fax"));
			setMobile(jsonObj.getString("mobile"));
			setEmail(jsonObj.getString("email"));
			setRemark(jsonObj.getString("remark"));
			setCustomer_type_id(jsonObj.getString("customer_type_id"));
			setCustomer_type_name(jsonObj.getString("customer_type_name"));
			setDiscount_text(jsonObj.getString("discount_text"));
			setAvailable_points(jsonObj.getString("available_points"));
			setBirthdate(jsonObj.getString("birthdate"));
			setStatus(jsonObj.getString("status"));
			setUtc_created_at(jsonObj.getString("utc_created_at"));
			setUtc_updated_at(jsonObj.getString("utc_updated_at"));
			setBirthday(jsonObj.getString("birthday"));
			
			setTax_exempt(jsonObj.getBoolean("tax_exempt"));
			
		} catch(JSONException e) {
			e.printStackTrace();
		}
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAlternate_code() {
		return alternate_code;
	}

	public void setAlternate_code(String alternate_code) {
		this.alternate_code = alternate_code;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getTin() {
		return tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCustomer_type_name() {
		return customer_type_name;
	}

	public void setCustomer_type_name(String customer_type_name) {
		this.customer_type_name = customer_type_name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCustomer_type_id() {
		return customer_type_id;
	}

	public void setCustomer_type_id(String customer_type_id) {
		this.customer_type_id = customer_type_id;
	}

	public String getDiscount_text() {
		return discount_text;
	}

	public void setDiscount_text(String discount_text) {
		this.discount_text = discount_text;
	}

	public String getAvailable_points() {
		return available_points;
	}

	public void setAvailable_points(String available_points) {
		this.available_points = available_points;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isTax_exempt() {
		return tax_exempt;
	}

	public void setTax_exempt(boolean tax_exempt) {
		this.tax_exempt = tax_exempt;
	}

	public int getPoint_to_amount_ratio() {
		return point_to_amount_ratio;
	}

	public void setPoint_to_amount_ratio(int point_to_amount_ratio) {
		this.point_to_amount_ratio = point_to_amount_ratio;
	}

	public String getUtc_created_at() {
		return utc_created_at;
	}

	public void setUtc_created_at(String utc_created_at) {
		this.utc_created_at = utc_created_at;
	}

	public String getUtc_updated_at() {
		return utc_updated_at;
	}

	public void setUtc_updated_at(String utc_updated_at) {
		this.utc_updated_at = utc_updated_at;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	
	
}
