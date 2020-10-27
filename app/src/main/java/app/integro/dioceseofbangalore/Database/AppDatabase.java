package app.integro.dioceseofbangalore.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import app.integro.dioceseofbangalore.models.ABEInstitutionData;
import app.integro.dioceseofbangalore.models.Institutions;
import app.integro.dioceseofbangalore.models.OtherInstitutionData;
import app.integro.dioceseofbangalore.models.Parishes;
import app.integro.dioceseofbangalore.models.ReligiousHouses;
import app.integro.dioceseofbangalore.models.ReligiousHousesData;
import app.integro.dioceseofbangalore.models.ReligiousInstitutionData;
import app.integro.dioceseofbangalore.models.WordOfGod;


@Database(entities = {Institutions.class, ABEInstitutionData.class,
        ReligiousInstitutionData.class,
        OtherInstitutionData.class,
        Parishes.class, WordOfGod.class, ReligiousHouses.class, ReligiousHousesData.class}, version = 6)

public abstract class AppDatabase extends RoomDatabase {
    public abstract MyDao myDao();
}
