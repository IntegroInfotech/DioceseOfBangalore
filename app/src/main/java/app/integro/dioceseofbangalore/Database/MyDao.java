package app.integro.dioceseofbangalore.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import app.integro.dioceseofbangalore.models.ABEInstitutionData;
import app.integro.dioceseofbangalore.models.Institutions;
import app.integro.dioceseofbangalore.models.OtherInstitutionData;
import app.integro.dioceseofbangalore.models.Parishes;
import app.integro.dioceseofbangalore.models.ReligiousHouses;
import app.integro.dioceseofbangalore.models.ReligiousHousesData;
import app.integro.dioceseofbangalore.models.ReligiousInstitutionData;
import app.integro.dioceseofbangalore.models.WordOfGod;


@Dao
public interface MyDao {

    @Query("SELECT * FROM institutions WHERE tag= :tag")
    List<Institutions> getInstitutions(String tag);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Institutions institutions);

    @Query("SELECT * FROM abeInstitutionData WHERE oid = :oid")
    List<ABEInstitutionData> getAbeInstitutionData(int oid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ABEInstitutionData abeInstitutionData);

    @Query("SELECT * FROM institutions")
    List<Institutions> getReligiousInstitution();

    @Query("SELECT * FROM religiousInstitutionData")
    List<ReligiousInstitutionData> getReligiousInstitutionData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ReligiousInstitutionData religiousInstitutionData);

    @Query("SELECT * FROM otherInstitutionData")
    List<OtherInstitutionData> getOtherInstitutionData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(OtherInstitutionData otherInstitutionData);

    @Query("SELECT * FROM parishes")
    List<Parishes> getParishes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Parishes parishes);

    @Query("SELECT * FROM wordofgod WHERE date = :date")
    List<WordOfGod> getWordOfGod(String date);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WordOfGod wordofgod);

    @Query("SELECT * FROM religiousHouses")
    List<ReligiousHouses> getReligiousHouses();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ReligiousHouses religiousHouses);

    @Query("SELECT * FROM ReligiousHousesData")
    List<ReligiousHousesData> getReligiousHousesData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ReligiousHousesData religiousHousesData);
}
