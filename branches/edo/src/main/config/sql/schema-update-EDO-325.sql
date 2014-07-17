ALTER TABLE "EDO"."EDO_ITEM_T"
	ADD ( "FILE_DESCRIPTION" VARCHAR2(4000) NULL );
COMMIT;

CREATE OR REPLACE VIEW "EDO"."EDO_ITEM_V" ( "ITEM_ID", "UPLOADER_USERNAME", "DOSSIER_ID", "UPLOAD_DATE", "FILE_NAME", "FILE_LOCATION", "FILE_DESCRIPTION", "NOTES", "ADDENDUM_ROUTED", "LAYER_LEVEL", "CREATE_DATE", "CREATED_BY", "LAST_UPDATE_DATE", "ITEM_TYPE_ID", "UPDATED_BY", "CONTENT_TYPE", "ROW_INDEX", "REVIEW_LAYER_DEF_ID", "CHECKLIST_SECTION_ID", "CHECKLIST_ITEM_ID", "ITEM_TYPE_NAME", "DESCRIPTION", "INSTRUCTIONS", "EXT_AVAILABILITY", "CANDIDATE_USERNAME", "REVIEW_LAYER_DESCRIPTION", "REVIEW_LEVEL" )
AS
SELECT i.item_id,
  i.uploader_username,
  i.dossier_id,
  i.upload_date,
  i.file_name,
  i.file_location,
  i.file_description,
  i.notes,
  i.addendum_routed,
  i.layer_level,
  i.create_date,
  i.created_by,
  i.last_update_date,
  i.item_type_id,
  i.updated_by,
  i.content_type,
  i.row_index,
  i.review_layer_def_id,
  ci.checklist_section_id,
  ci.checklist_item_id,
  it.item_type_name,
  it.description,
  it.instructions,
  it.ext_availability,
  do.candidate_username,
  rl.description AS review_layer_description,
  rl.review_level
 FROM edo_item_t i
  INNER JOIN edo_checklist_item_t ci
 ON i.checklist_item_id = ci.checklist_item_id
  INNER JOIN edo_item_type_t it ON i.item_type_id = it.item_type_id
  INNER JOIN edo_dossier_t do ON i.dossier_id = do.dossier_id
  LEFT OUTER JOIN edo_review_layer_def_t rl
 ON i.review_layer_def_id = rl.review_layer_def_id;

 COMMIT;
