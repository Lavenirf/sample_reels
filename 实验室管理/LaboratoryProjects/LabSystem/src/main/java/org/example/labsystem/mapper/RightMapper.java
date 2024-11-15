package org.example.labsystem.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.labsystem.pojo.entity.Right;

import java.util.List;
@Mapper
public interface RightMapper{
    List<Right> getRightList();

    boolean updateRightList(Right right);

    @Delete("delete from rights where id =#{id}")
    boolean deleteRight(int id);
}
