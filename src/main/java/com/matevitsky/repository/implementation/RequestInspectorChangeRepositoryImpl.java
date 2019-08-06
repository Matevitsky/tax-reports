package com.matevitsky.repository.implementation;

import com.matevitsky.entity.Request;
import com.matevitsky.repository.interfaces.RequestInspectorChangeRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.matevitsky.controller.constant.ParameterConstant.CLIENT_ID;
import static com.matevitsky.controller.constant.ParameterConstant.REQUEST_ID;

public class RequestInspectorChangeRepositoryImpl extends CrudRepositoryImpl<Request> implements RequestInspectorChangeRepository {

    private static final Logger LOGGER = Logger.getLogger(RequestInspectorChangeRepositoryImpl.class);

    private static final String CREATE_REQUEST = "INSERT INTO requests (client_id) VALUES ('%d');";
    private static final String DELETE_REQUEST = "DELETE FROM requests WHERE request_id='%d'";
    private static final String SELECT_REQUEST_BY_ID = "SELECT * FROM requests WHERE request_id='%d'";
    private static final String SELECT_ALL_REQUESTS = "SELECT * FROM requests";
    private static final String DELETE_REQUEST_BY_CLIENT_ID = "DELETE FROM requests WHERE client_id='%d'";



    @Override
    protected String getCreateQuery(Request request) {
        return String.format(CREATE_REQUEST, request.getClientId());
    }

    @Override
    protected String getDeleteByIdQuery(int id) {
        return String.format(DELETE_REQUEST, id);
    }

    @Override
    protected String getUpdateQuery(Request entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getByIdQuery(int id) {
        return String.format(SELECT_REQUEST_BY_ID, id);
    }

    @Override
    protected String getAllQuery() {
        return SELECT_ALL_REQUESTS;
    }

    @Override
    protected List<Request> mapToList(ResultSet resultSet) {

        List<Request> allRequestList = new ArrayList<>();
        Request request;

        try {
            while (resultSet.next()) {
                request = mapToObject(resultSet);
                allRequestList.add(request);
            }
        } catch (SQLException e) {
            LOGGER.warn("GetAll method return empty RowSet");
        }

        return allRequestList;
    }

    @Override
    protected Request mapToObject(ResultSet resultSet) {

        Request request = null;
        try {
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
            }
            int id = resultSet.getInt(REQUEST_ID);
            int clientId = resultSet.getInt(CLIENT_ID);
            request = new Request(id, clientId);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return request;
    }

    @Override
    public void deleteByClientID(int clientId, Connection connection) throws SQLException {

        String sqlQuery = String.format(DELETE_REQUEST_BY_CLIENT_ID, clientId);

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            if (statement.executeUpdate() == 0) {
                LOGGER.error("deleteByClientId entity failed for id " + clientId + " , no rows affected.");
            }
        }
    }

}
