

<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackageservice}.impl;


import ${basepackagedao}.${className}Dao;
import ${basepackagedao}.base.BaseDao;
import ${basepackage}.${className};
import ${basepackagequery}.${className}Query;
import ${basepackageservice}.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wing
 *
 */
<#include "/java_imports.include">
@Service("${classNameLower}Service")
public class ${className}Service extends BaseServiceImpl<${className}, ${className}Query>{

    @Autowired
    private ${className}Dao ${classNameLower}Dao;

    @Override
    protected BaseDao<${className}, ${className}Query> getDao(){
        return ${classNameLower}Dao;
    }
}
