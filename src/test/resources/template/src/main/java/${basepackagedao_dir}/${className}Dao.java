<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackagedao};

import ${basepackagedao}.base.BaseDao;
import ${basepackage}.${className};
import ${basepackagequery}.${className}Query;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by rapid-generator.
 */
<#include "/java_imports.include">
@Mapper
public interface ${className}Dao extends BaseDao<${className}, ${className}Query>{

}
