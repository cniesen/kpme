alter table edo_dossier_t
  add ( candidacy_year number(8) NULL );
COMMIT;

update EDO_DOSSIER_T set CANDIDACY_YEAR = extract(year from DUE_DATE);
COMMIT;

alter table edo_candidate_t
  drop column candidacy_year;
COMMIT;
