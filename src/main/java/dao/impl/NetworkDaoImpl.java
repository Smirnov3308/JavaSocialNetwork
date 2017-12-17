package dao.impl;

import dao.BaseDao;
import dao.NetworkDao;
import model.Network;

public class NetworkDaoImpl extends BaseDao implements NetworkDao {
    @Override
    protected void createTable() {
        System.out.println("create table Network");
    }

    @Override
    public Network getNetwork() {
        System.out.println("getting network from DAO");
        return new Network();
    }
}