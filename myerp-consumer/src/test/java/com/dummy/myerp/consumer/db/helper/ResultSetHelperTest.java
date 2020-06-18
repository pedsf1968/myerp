package com.dummy.myerp.consumer.db.helper;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class ResultSetHelperTest {
   private static final Integer valueInteger = 1;
   private static final Long valueLong = 123456789L;
   private static final Date valueDate = new Date(2020-06-18);
   private static final String LABEL_INTEGER = "labelInteger";
   private static final String LABEL_LONG = "labelLong";
   private static final String LABEL_DATE = "labelDate";

   @Mock
   ResultSet resultSet;

   @BeforeEach
   protected void beforeEach() throws SQLException {
      Mockito.lenient().when(resultSet.getInt(LABEL_INTEGER)).thenReturn(valueInteger);
      Mockito.lenient().when(resultSet.getLong(LABEL_LONG)).thenReturn(valueLong);
      Mockito.lenient().when(resultSet.getDate(LABEL_DATE)).thenReturn(valueDate);
   }

   @Test
   @Tag("getInteger")
   @DisplayName("Verify that getInteger return Integer value")
   void getInteger_returnIntegerValue_ofIntegerColumn() throws SQLException {

      assertThat(ResultSetHelper.getInteger(resultSet,LABEL_INTEGER)).isEqualTo(valueInteger);
   }

   @Test
   @Tag("getInteger")
   @DisplayName("Verify that getInteger throws SQLException with null ResultSet")
   void getInteger_throwsException_ofNullResultSet() throws SQLException {
      assertThrows(SQLException.class, () -> {
         ResultSetHelper.getInteger(null,LABEL_INTEGER);});
   }

   @Test
   @Tag("getInteger")
   @DisplayName("Verify that getInteger throws SQLException with null column name")
   void getInteger_throwsException_ofNullColumnName() throws SQLException {
      assertThrows(SQLException.class, () -> {
         ResultSetHelper.getInteger(resultSet,null);});
   }

   @Test
   @Tag("getLong")
   @DisplayName("Verify that getLong return Long value")
   void getLong_returnLongValue_ofLongColumn() throws SQLException{
      assertThat(ResultSetHelper.getLong(resultSet,LABEL_LONG)).isEqualTo(valueLong);
   }

   @Test
   @Tag("getLong")
   @DisplayName("Verify that getLong throws SQLException with null ResultSet")
   void getLong_throwsException_ofNullResultSet() throws SQLException {
      assertThrows(SQLException.class, () -> {
         ResultSetHelper.getLong(null,LABEL_LONG);});
   }

   @Test
   @Tag("getLong")
   @DisplayName("Verify that getLong throws SQLException with null column name")
   void getLong_throwsException_ofNullColumnName() throws SQLException {
      assertThrows(SQLException.class, () -> {
         ResultSetHelper.getLong(resultSet,null);});
   }

   @Test
   @Tag("getDate")
   @DisplayName("Verify that getDate return Date value")
   void getDate_returnDateValue_ofDateColumn()throws SQLException {
      DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

      assertThat(ResultSetHelper.getDate(resultSet,LABEL_DATE)).isEqualTo(formatter.format(valueDate));
   }

   @Test
   @Tag("getDate")
   @DisplayName("Verify that getDate throws SQLException with null ResultSet")
   void getDate_throwsException_ofNullResultSet() throws SQLException {
      assertThrows(SQLException.class, () -> {
         ResultSetHelper.getDate(null,LABEL_DATE);});
   }

   @Test
   @Tag("getDate")
   @DisplayName("Verify that getDate throws SQLException with null column name")
   void getDate_throwsException_ofNullColumnName() throws SQLException {
      assertThrows(SQLException.class, () -> {
         ResultSetHelper.getDate(resultSet,null);});
   }

}