
package com.isl.admin.commission.remarks.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "index",
    "parent",
    "nodetype",
    "name",
    "type",
    "displaytype",
    "nodes",
    "editarea",
    "isconfirm"
})
public class RemarkTreeView {

    @JsonProperty("index")
    private int index;
    @JsonProperty("parent")
    @Valid
    private List<Parent> parent = new ArrayList<Parent>();
    @JsonProperty("nodetype")
    private int nodetype;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("displaytype")
    private String displaytype;
    @JsonProperty("nodes")
    @Valid
    private List<Node_> nodes = new ArrayList<Node_>();
    @JsonProperty("editarea")
    private String editarea;
    @JsonProperty("isconfirm")
    private boolean isconfirm;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The index
     */
    @JsonProperty("index")
    public int getIndex() {
        return index;
    }

    /**
     * 
     * @param index
     *     The index
     */
    @JsonProperty("index")
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * 
     * @return
     *     The parent
     */
    @JsonProperty("parent")
    public List<Parent> getParent() {
        return parent;
    }

    /**
     * 
     * @param parent
     *     The parent
     */
    @JsonProperty("parent")
    public void setParent(List<Parent> parent) {
        this.parent = parent;
    }

    /**
     * 
     * @return
     *     The nodetype
     */
    @JsonProperty("nodetype")
    public int getNodetype() {
        return nodetype;
    }

    /**
     * 
     * @param nodetype
     *     The nodetype
     */
    @JsonProperty("nodetype")
    public void setNodetype(int nodetype) {
        this.nodetype = nodetype;
    }

    /**
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The displaytype
     */
    @JsonProperty("displaytype")
    public String getDisplaytype() {
        return displaytype;
    }

    /**
     * 
     * @param displaytype
     *     The displaytype
     */
    @JsonProperty("displaytype")
    public void setDisplaytype(String displaytype) {
        this.displaytype = displaytype;
    }

    /**
     * 
     * @return
     *     The nodes
     */
    @JsonProperty("nodes")
    public List<Node_> getNodes() {
        return nodes;
    }

    /**
     * 
     * @param nodes
     *     The nodes
     */
    @JsonProperty("nodes")
    public void setNodes(List<Node_> nodes) {
        this.nodes = nodes;
    }

    /**
     * 
     * @return
     *     The editarea
     */
    @JsonProperty("editarea")
    public String getEditarea() {
        return editarea;
    }

    /**
     * 
     * @param editarea
     *     The editarea
     */
    @JsonProperty("editarea")
    public void setEditarea(String editarea) {
        this.editarea = editarea;
    }

    /**
     * 
     * @return
     *     The isconfirm
     */
    @JsonProperty("isconfirm")
    public boolean isIsconfirm() {
        return isconfirm;
    }

    /**
     * 
     * @param isconfirm
     *     The isconfirm
     */
    @JsonProperty("isconfirm")
    public void setIsconfirm(boolean isconfirm) {
        this.isconfirm = isconfirm;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
