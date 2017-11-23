<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackagequery};

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rapid-generator.
 */
public class ${className}Query {
    <#list table.columns as column>

    private ${column.simpleJavaType} ${column.columnNameLower};
    <#if column.isDateTimeColumn>

    // 【时间段查询】
    private ${column.simpleJavaType} ${column.columnNameLower}Begin;
    private ${column.simpleJavaType} ${column.columnNameLower}End;
    </#if>
    <#if column.sqlName == "status">

    // 【排除状态查询】
    private ${column.simpleJavaType} ${column.columnNameLower}Exclude;
    </#if>
    </#list>

    // 【in查询使用，形式：1,2,3,4,5】
    private String ids;

    // 【排序】
    private String sortColumns;
    private int sortType;

    // 【like】
    private Map<String, Integer> selectType = new HashMap<>();

    // 【分页】
    private Integer offset;
    private Integer limit;

    <@generateJavaColumns/>
    <@generateJavaOneToMany/>
    <@generateJavaManyToOne/>

}

<#macro generateJavaColumns>
    public String getIds() {
        return ids;
    }

    public ${className}Query withIds(String ids) {
        this.ids = ids;
        return this;
    }

    public String getSortColumns() {
        return sortColumns;
    }

    public ${className}Query withSortColumns(String sortColumns) {
        this.sortColumns = sortColumns;
        return this;
    }

    public int getSortType() {
        return sortType;
    }

    public ${className}Query withSortType(int sortType) {
        this.sortType = sortType;
        return this;
    }

    public Map<String, Integer> getSelectType() {
        return selectType;
    }

    public ${className}Query withSelectType(Map<String, Integer> selectType) {
        this.selectType = selectType;
        return this;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public ${className}Query withOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public ${className}Query withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public ${className}Query withPaging(Integer page, Integer pageSize) {
        this.offset = (page - 1) * pageSize;
        this.limit = pageSize;
        return this;
    }

    <#list table.columns as column>
    public ${className}Query with${column.columnName}(${column.simpleJavaType} ${column.columnNameLower}) {
        this.${column.columnNameLower} = ${column.columnNameLower};
        return this;
    }

    public ${column.simpleJavaType} get${column.columnName}() {
        return this.${column.columnNameLower};
    }

    <#if column.isDateTimeColumn>
    public ${className}Query with${column.columnName}Begin(${column.simpleJavaType} ${column.columnNameLower}Begin) {
        this.${column.columnNameLower}Begin = ${column.columnNameLower}Begin;
        return this;
    }

    public ${column.simpleJavaType} get${column.columnName}Begin() {
        return this.${column.columnNameLower}Begin;
    }

    public ${className}Query with${column.columnName}End(${column.simpleJavaType} ${column.columnNameLower}End) {
        this.${column.columnNameLower}End = ${column.columnNameLower}End;
        return this;
    }

    public ${column.simpleJavaType} get${column.columnName}End() {
        return this.${column.columnNameLower}End;
    }

    </#if>
    <#if column.sqlName == "status">
    public ${className}Query with${column.columnName}Exclude(${column.simpleJavaType} ${column.columnNameLower}Exclude) {
        this.${column.columnNameLower}Exclude = ${column.columnNameLower}Exclude;
        return this;
    }

    public ${column.simpleJavaType} get${column.columnName}Exclude() {
        return this.${column.columnNameLower}Exclude;
    }

    </#if>
    </#list>
</#macro>

<#macro generateJavaOneToMany>
    <#list table.exportedKeys.associatedTables?values as foreignKey>
    <#assign fkSqlTable = foreignKey.sqlTable>
    <#assign fkTable    = fkSqlTable.className>
    <#assign fkPojoClass = fkSqlTable.className>
    <#assign fkPojoClassVar = fkPojoClass?uncap_first>

    private Set ${fkPojoClassVar}s = new HashSet(0);
    public void set${fkPojoClass}s(Set<${fkPojoClass}> ${fkPojoClassVar}){
        this.${fkPojoClassVar}s = ${fkPojoClassVar};
    }

    public Set<${fkPojoClass}> get${fkPojoClass}s() {
        return ${fkPojoClassVar}s;
    }
    </#list>
</#macro>

<#macro generateJavaManyToOne>
    <#list table.importedKeys.associatedTables?values as foreignKey>
    <#assign fkSqlTable = foreignKey.sqlTable>
    <#assign fkTable    = fkSqlTable.className>
    <#assign fkPojoClass = fkSqlTable.className>
    <#assign fkPojoClassVar = fkPojoClass?uncap_first>

    private ${fkPojoClass} ${fkPojoClassVar};

    public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
        this.${fkPojoClassVar} = ${fkPojoClassVar};
    }

    public ${fkPojoClass} get${fkPojoClass}() {
        return ${fkPojoClassVar};
    }
    </#list>
</#macro>
