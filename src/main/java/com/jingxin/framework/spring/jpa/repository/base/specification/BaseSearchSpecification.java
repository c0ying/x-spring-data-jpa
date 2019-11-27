package com.jingxin.framework.spring.jpa.repository.base.specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.Assert;

import com.jingxin.framework.spring.jpa.repository.base.specification.BaseSpecification.SearchAction;
import com.jingxin.framework.spring.jpa.repository.base.specification.BaseSpecification.SearchType;

public class BaseSearchSpecification extends BaseAggregateSpecification{
	
	protected List<BaseSearchSpecification> combine_actions = new ArrayList<BaseSearchSpecification>(0);
	protected List<SearchAction> actions = new ArrayList<SearchAction>(0);
	
	protected String type = "conjunction";//actions连接模式
	protected String combine_type = "conjunction";//多个SearchSpecification连接模式
	
	protected boolean negative = false;//是否对actions取反
	
	public static BaseSearchSpecification getInstance(){
		return new BaseSearchSpecification();
	}

	public BaseSearchSpecification LT(String property, Object value){
		actions.add(new SearchAction(SearchType.LT, property, value));
		return this;
	}
	
	public BaseSearchSpecification LTE(String property, Object value){
		actions.add(new SearchAction(SearchType.LTE, property, value));
		return this;
	}
	
	public BaseSearchSpecification GT(String property, Object value){
		actions.add(new SearchAction(SearchType.GT, property, value));
		return this;
	}
	
	public BaseSearchSpecification GTE(String property, Object value){
		actions.add(new SearchAction(SearchType.GTE, property, value));
		return this;
	}
	
	public BaseSearchSpecification LIKE(String property, String value){
		actions.add(new SearchAction(SearchType.LIKE, property, value));
		return this;
	}
	
	public BaseSearchSpecification EQ(String property, Object value){
		actions.add(new SearchAction(SearchType.EQ, property, value));
		return this;
	}
	
	public BaseSearchSpecification START_WITH(String property, String value){
		actions.add(new SearchAction(SearchType.STARTW, property, value));
		return this;
	}
	
	public BaseSearchSpecification END_WITH(String property, String value){
		actions.add(new SearchAction(SearchType.ENDW, property, value));
		return this;
	}
	
	public BaseSearchSpecification IN(String property, Object[] value){
		actions.add(new SearchAction(SearchType.IN, property, value));
		return this;
	}
	
	public BaseSearchSpecification NOT_IN(String property, Object[] value){
		actions.add(new SearchAction(SearchType.NOTIN, property, value));
		return this;
	}
	
	public BaseSearchSpecification ISNULL(String property){
		actions.add(new SearchAction(SearchType.ISNULL, property, null));
		return this;
	}
	
	public BaseSearchSpecification NOTNULL(String property){
		actions.add(new SearchAction(SearchType.NOTNULL, property, null));
		return this;
	}
	
	public BaseSearchSpecification NOTEQ(String property, String value){
		actions.add(new SearchAction(SearchType.NOTEQ, property, value));
		return this;
	}
	
	public BaseSearchSpecification NOTLIKE(String property, String value){
		actions.add(new SearchAction(SearchType.NOTLIKE, property, value));
		return this;
	}
	
	public BaseSearchSpecification NOT() {
		this.negative = true;
		return this;
	}
	
	public BaseSearchSpecification and(BaseSearchSpecification... searchSpecification){
		Assert.isTrue(searchSpecification != null && searchSpecification.length > 0, 
						"searchSpecification length must greater than zero");
		combine_actions.addAll(Arrays.asList(searchSpecification));
		this.combine_type = "conjunction";
		return this;
	}
	
	public BaseSearchSpecification or(BaseSearchSpecification... searchSpecification){
		Assert.isTrue(searchSpecification != null && searchSpecification.length > 0, 
						"searchSpecification length must greater than zero");
		combine_actions.addAll(Arrays.asList(searchSpecification));
		this.combine_type = "disjunction";
		return this;
	}
	
	public BaseSearchSpecification conjunction(){
		this.type = "conjunction";
		return this;
	}
	public BaseSearchSpecification disjunction(){
		this.type = "disjunction";
		return this;
	}
	
	public boolean isConjunction(){
		if (this.type.equalsIgnoreCase("conjunction")) {
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isDisjunction(){
		if (this.type.equalsIgnoreCase("disjunction")) {
			return true;
		}else{
			return false;
		}
	}
	
	public boolean hasCombine(){
		if (this.combine_actions.size() > 0) {
			return true;
		}else{
			return false;
		}
	}
	
	public String getType(){
		return this.type;
	}
	public String getCombineType() {
		return combine_type;
	}
	public boolean isNegative() {
		return negative;
	}
	
	public List<SearchAction> getActions() {
		return actions;
	}

	public List<BaseSearchSpecification> getCombineActions() {
		return combine_actions;
	}

}
