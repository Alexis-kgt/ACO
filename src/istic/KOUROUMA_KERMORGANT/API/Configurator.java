package istic.KOUROUMA_KERMORGANT.API;

import java.util.Collection;

public interface Configurator {

  Configuration getConfiguration();

  Boolean getVariants();
  
  Collection<Category> getCategories();
  
  Collection<PartType> getVariantsForCategory(Category c);
  
  Collection<PartType> getIncompatibilities(PartType pt);
  
  Collection<PartType> getRequirements(PartType pt);
  
  String toString();

}