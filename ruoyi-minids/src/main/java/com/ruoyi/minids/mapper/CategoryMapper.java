package com.ruoyi.minids.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import com.ruoyi.minids.domain.Category;

import org.apache.ibatis.annotations.Delete;
import java.util.List;

@Mapper
public interface CategoryMapper {
        /**
         * 查询下级部门数量
         * 
         * @param category 分类信息
         * @return 结果
         */
        public int selectProductionCategoryCount(Category category);

        public List<String> selectProductionCategoryTree(long categoryId);

        /**
         * 查询部门是否存在用户
         * 
         * @param deptId 部门ID
         * @return 结果
         */
        public int checkCategoryExistProduction(Long categoryId);

        /**
         * 查询部门管理数据
         * 
         * @param dept 部门信息
         * @return 部门信息集合
         */
        public List<Category> selectCategoryAll();

        public List<Category> selectCategoryList(Category category);

        /**
         * 删除部门管理信息
         * 
         * @param deptId 部门ID
         * @return 结果
         */
        public int deleteCategoryById(Long categoryId);

        /**
         * 新增部门信息
         * 
         * @param dept 部门信息
         * @return 结果
         */
        public int insertCategory(Category category);

        /**
         * 修改部门信息
         * 
         * @param dept 部门信息
         * @return 结果
         */
        public int updateCategory(Category category);

        /**
         * 修改子元素关系
         * 
         * @param categories 子元素
         * @return 结果
         */
        public int updateCategoryChildren(@Param("category") List<Category> categories);

        /**
         * 根据部门ID查询信息
         * 
         * @param categoryId 部门ID
         * @return 部门信息
         */
        public Category selectCategoryById(Long categoryId);

        /**
         * 校验部门名称是否唯一
         * 
         * @param deptName 部门名称
         * @param parentId 父部门ID
         * @return 结果
         */
        public Category checkCategoryNameUnique(@Param("categoryName") String categoryName,
                        @Param("parentId") Long parentId);

        /**
         * 根据角色ID查询部门
         *
         * @param categoryId 角色ID
         * @return 部门列表
         */
        public List<String> selectCategoryTree(Long categoryId);

        /**
         * 修改所在部门正常状态
         * 
         * @param categories 部门ID组
         */
        public void updateCategoryStatusNormal(Long[] categories);

        /**
         * 根据ID查询所有子部门
         * 
         * @param deptId 部门ID
         * @return 部门列表
         */
        public List<Category> selectChildrenCategoryById(Long categoryId);

        /**
         * 根据ID查询所有子部门（正常状态）
         * 
         * @param categoryId 分类ID
         * @return 子部门数
         */
        public int selectNormalChildrenCategoryById(Long categoryId);
}
